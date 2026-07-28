Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

Push-Location (Join-Path $PSScriptRoot "..")
try {
    .\mvnw.cmd clean package -DskipTests
}
finally {
    Pop-Location
}
