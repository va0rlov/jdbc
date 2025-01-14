# Step 1: Build the project
Write-Host "Building the project..."
.\gradlew clean build

# Check if the build was successful
if ($LASTEXITCODE -ne 0) {
    Write-Host "Error: Failed to build the project."
    exit $LASTEXITCODE
}

# Step 2: Run the JAR file
Write-Host "Starting the application..."
java -jar build\libs\jdbc1-0.0.1-SNAPSHOT.jar contacts

# Check if the application started successfully
if ($LASTEXITCODE -ne 0) {
    Write-Host "Error: Failed to start the application."
    exit $LASTEXITCODE
}