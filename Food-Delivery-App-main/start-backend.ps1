# Start Spring Boot Backend
Write-Host "Starting Spring Boot Backend..." -ForegroundColor Green
Write-Host "Backend will be available at: http://localhost:8080" -ForegroundColor Yellow
Write-Host "Swagger UI will be available at: http://localhost:8080/swagger-ui.html" -ForegroundColor Yellow
Write-Host ""

# Check MongoDB
$mongodbRunning = Get-NetTCPConnection -LocalPort 27017 -ErrorAction SilentlyContinue
if ($mongodbRunning) {
    Write-Host "✓ MongoDB is running on port 27017" -ForegroundColor Green
} else {
    Write-Host "⚠ WARNING: MongoDB might not be running on port 27017" -ForegroundColor Yellow
    Write-Host "Please start MongoDB if it's not running" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "Starting backend server..." -ForegroundColor Cyan
.\mvnw.cmd spring-boot:run

