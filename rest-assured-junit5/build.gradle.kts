plugins {
    java
}

dependencies {
    implementation(project(":common"))

    testImplementation(platform("org.junit:junit-bom:5.12.2"))
    testImplementation("org.junit.platform:junit-platform-suite")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")


    testImplementation("io.rest-assured:rest-assured:5.5.1")
}