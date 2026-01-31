# PowerShell script to run the Food Delivery App

# Set JAVA_HOME
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-8.0.472.8-hotspot"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

# Change to project directory
$projectDir = "c:\Users\shwet\Desktop\Mini projects\food-deliver-app"
Set-Location $projectDir

Write-Host "======================================="
Write-Host "Food Delivery App - Local Setup"
Write-Host "======================================="
Write-Host ""

# Check if MongoDB is running
Write-Host "Checking MongoDB..."
$mongod = Get-Process mongod -ErrorAction SilentlyContinue
if ($mongod) {
    Write-Host "✓ MongoDB is running"
} else {
    Write-Host "✗ MongoDB is NOT running"
    Write-Host "Please start MongoDB with: mongod --dbpath C:\path\to\data\db"
    Write-Host ""
}

# Check Java
Write-Host "Checking Java..."
$javaVersion = & java -version 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Java is installed"
} else {
    Write-Host "✗ Java is NOT installed"
    exit 1
}

Write-Host ""
Write-Host "Starting Spring Boot backend..."
Write-Host "API will be available at: http://localhost:8080"
Write-Host "Swagger UI available at: http://localhost:8080/swagger-ui.html"
Write-Host ""

# Run Maven
& mvn.cmd clean spring-boot:run
