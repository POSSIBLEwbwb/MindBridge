Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

Push-Location (Join-Path $PSScriptRoot "..")
try {
    .\mvnw.cmd -pl functioncall spring-boot:run
}
finally {
    Pop-Location
}
