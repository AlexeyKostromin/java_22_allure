plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

group = "com.alexey_k"
version = "1.0-SNAPSHOT"

allure {
    version.set("2.19.0")
    adapter {// responsible for folder builds/allure-results
        // Configure version for io.qameta.allure:allure-* adapters
        // It defaults to allure.version
        allureJavaVersion.set("2.19.0")
        aspectjVersion.set("1.9.5")

        // Customize environment variables for launching Allure
        environment.put("JAVA_HOME", "/path/to/java_home")

        autoconfigure.set(true)
        autoconfigureListeners.set(true)
        aspectjWeaver.set(true)

        // By default, categories.json is detected in src/test/resources/../categories.json,
        // However, it would be better to put the file in a well-known location and configure it explicitly
        categoriesFile.set(layout.projectDirectory.file("config/allure/categories.json"))
        frameworks {
            junit5 {
                // Defaults to allureJavaVersion
                adapterVersion.set("2.19.0")
                enabled.set(true)
                // Enables allure-junit4 default test listeners via META-INF/services/...
                autoconfigureListeners.set(true)
            }
            junit4 {
                // same as junit5
            }
            testng {
                // same as junit5
            }
            spock
            cucumberJvm
            // Alternative syntax: cucumberJvm(2) {...}
            cucumber2Jvm
            cucumber3Jvm
            cucumber4Jvm
            cucumber5Jvm
            cucumber6Jvm
        }
    }
}


repositories {
    mavenCentral()
}

//dependencies {
//    testImplementation(platform("org.junit:junit-bom:5.9.1"))
//    testImplementation("org.junit.jupiter:junit-jupiter")
//}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("com.codeborne:selenide:6.18.0")
}
tasks.test {
    useJUnitPlatform()
}