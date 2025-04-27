plugins {
    java
}

dependencies {
    implementation(project(":common"))

    testImplementation(platform("org.junit:junit-bom:5.12.2"))
    testImplementation("org.junit.platform:junit-platform-suite")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testImplementation(platform("io.cucumber:cucumber-bom:7.22.1"))
    testImplementation("io.cucumber:cucumber-java")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")
    testImplementation("io.cucumber:cucumber-picocontainer")

//    testImplementation(platform("org.assertj:assertj-bom:3.27.3"))
//    testImplementation("org.assertj:assertj-core")

    testImplementation("io.rest-assured:rest-assured:5.5.1")
}

tasks.withType<Test> {
    systemProperty("cucumber.glue", "com.github.j7an.bdd.steps")
    systemProperty("cucumber.features", "src/test/resources/features")
    systemProperty("cucumber.plugin", "pretty, html:build/reports/cucumber/cucumber-report.html")
//    systemProperty("cucumber.filter.tags", "@smoke and not @wip")
//    systemProperty("cucumber.execution.parallel.enabled", "true")
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}