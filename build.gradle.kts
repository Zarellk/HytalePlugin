plugins {
    id("java")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(files("libs/HytaleServer.jar"))
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Copy>("copyToMods") {
    // Берём готовый JAR-файл
    from(tasks.jar)
    // Указываем путь к папке модов вашего лаунчера
    into("C:/Users/User/AppData/Roaming/HLauncher/UserData/Mods/")
}

// Автоматически запускаем копирование после сборки
tasks.build {
    dependsOn("copyToMods")
}
tasks.jar {
    finalizedBy("copyToMods")
}
