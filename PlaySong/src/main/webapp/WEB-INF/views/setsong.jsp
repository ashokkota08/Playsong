<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Set Song Time</title>
    <script>
        function submitForm() {
            let time = document.getElementById("time").value;
            let filePath = document.getElementById("filePath").value;
            
            if (!time || !filePath) {
                alert("Please enter both time and file path.");
                return;
            }
            
            fetch("http://localhost:8080/api/set-song-time", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ time, path: filePath })
            })
            .then(response => response.text()) // Expecting a text response from the backend
            .then(data => {
                alert(data); // Display response from the server
            })
            .catch(error => {
                console.error("Error setting song time:", error);
                alert("Failed to set song time.");
            });
        }

        function getSongDetails() {
            fetch("http://localhost:8080/api/get-song-time")
            .then(response => response.text()) // Expecting a text response from the backend
            .then(data => {
                document.getElementById("songDetails").innerText = data; // Display the latest song schedule
            })
            .catch(error => {
                console.error("Error fetching song details:", error);
                alert("Failed to fetch song details.");
            });
        }

        function deleteSong() {
            fetch("http://localhost:8080/api/delete-song-time", {
                method: "DELETE"
            })
            .then(response => response.text()) // Expecting a text response from the backend
            .then(data => {
                alert(data); // Show response message
                document.getElementById("songDetails").innerText = "No song schedule found."; // Clear details
            })
            .catch(error => {
                console.error("Error deleting song:", error);
                alert("Failed to delete song schedule.");
            });
        }
    </script>
</head>
<body>
    <div style="width: 350px; margin: 50px auto; padding: 20px; border: 1px solid #ccc; text-align: center;">
        <h2>Set Song Time</h2>
        <input type="text" id="time" placeholder="Enter time (HH:MM)" style="display: block; width: 100%; margin-bottom: 10px; padding: 5px;">
        <input type="text" id="filePath" placeholder="Enter file path" style="display: block; width: 100%; margin-bottom: 10px; padding: 5px;">
        <button onclick="submitForm()" style="padding: 10px 20px; background-color: blue; color: white; border: none; cursor: pointer;">Submit</button>

        <h3>Latest Song Schedule:</h3>
        <p id="songDetails">No song schedule found.</p>
        <button onclick="getSongDetails()" style="padding: 10px 20px; margin-top: 10px; background-color: green; color: white; border: none; cursor: pointer;">Get Latest Song</button>
        <button onclick="deleteSong()" style="padding: 10px 20px; margin-top: 10px; background-color: red; color: white; border: none; cursor: pointer;">Delete Song</button>
    </div>
</body>
</html>
