
//this is to store the backend API,which is running on my local computer at port 8080 and we send request to this API to get employees, add employees and analyze performance.
const API_URL = "http://localhost:8080/api/bell-curve";

// Function to fetch and display employees
function fetchEmployees() {
    fetch(`${API_URL}/employees`)  //this is asking the backend , give me the list of employees
        .then(response => response.json()) // converts the API response into a format,JS can understand (JSON)
        .then(data => {     //this runs after we get the data
            let html = "<h3>Employee List:</h3><ul>";  //we create string html with unordered list
            data.forEach(emp => {  //for each employee in data, we add a list item
                html += `<li>ID: ${emp.id}, Name: ${emp.name}, Rating: ${emp.ratingCategory}</li>`; // Fixed template literals
            });
            html += "</ul>";
            document.getElementById("employeeList").innerHTML = html; //puts the list inside the webpage
        })
        .catch(error => console.error("Error fetching employees:", error)); //if something goes wrong, it show an error in the console
}



//Adding a new Employee
document.getElementById("employeeForm").addEventListener("submit", function(event) {
    event.preventDefault(); //stops the page from refreshing when the button is clicked


    //find the inputbox where the user enter the employee name, take whatever is typed inside and store it in a variable name.
    const name = document.getElementById("name").value;
    const ratingCategory = document.getElementById("ratingCategory").value;


    //sending data to the backend
    fetch(`${API_URL}/add`, {  //this is a backend address where we are sending the data
        method: "POST",
        headers: {
            "Content-Type": "application/json"  
        },
        body: JSON.stringify({ name, ratingCategory })  //convert to JSON format before sending
    })
    .then(response => {  //handing the response
        if (!response.ok) {
            throw new Error("Failed to add employee");
        }
        return response.json();
    })
    .then(data => {  //showing sucessfull message
        alert("Employee Added Successfully!");
        document.getElementById("employeeForm").reset();
        fetchEmployees(); // Refresh list after adding
    })
    .catch(error => console.error("Error:", error));
});


// Function to analyze employee performance
function analyzePerformance() {
    fetch(`${API_URL}/analyze`)  //asks the backend
        .then(response => response.json())  //convert the response into JSON format
        .then(data => {  //processing the data
            let html = "<h3>Performance Analysis:</h3>";
            html += "<h4>Actual Percentage:</h4><ul>";
            for (const [category, percentage] of Object.entries(data.actualPercentage)) {
                html += `<li>${category}: ${percentage.toFixed(2)}%</li>`; 
            }
            html += "</ul>";


            //showing the deviation(difeerence from expected)
            html += "<h4>Deviation:</h4><ul>";
            for (const [category, deviation] of Object.entries(data.deviation)) {
                html += `<li>${category}: ${deviation.toFixed(2)}%</li>`; 
            }
            html += "</ul>";


            //showing adjustments needed
            html += "<h4>Adjustments:</h4><ul>";
            data.adjustments.forEach(emp => {
                html += `<li>Move Employee ${emp.name} (ID: ${emp.id}) from Category ${emp.ratingCategory}</li>`; 
            });
            html += "</ul>";

            //displaying the result
            document.getElementById("analysisResults").innerHTML = html;
        })
        .catch(error => console.error("Error analyzing performance:", error));
}
