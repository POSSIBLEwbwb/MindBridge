Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

Push-Location (Join-Path $PSScriptRoot "..")
try {
    .\mvnw.cmd -pl rag spring-boot:run
}
finally {
    Pop-Location
}
