val ktorVersion: String by project
val ktxVersion: String by project
val kotlinxCoroutinesVersion: String by project
val gdxVersion: String by project
val kotlinVersion: String by project
val ktorSocketVersion: String by project
val lmlVersion: String by project

group = "adapters:rest"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.badlogicgames.gdx:gdx:$gdxVersion")
    implementation("com.crashinvaders.lml:gdx-kiwi:$lmlVersion")
    implementation("com.crashinvaders.lml:gdx-lml:$lmlVersion")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-websocket:$ktorSocketVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.16.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinxCoroutinesVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
}

tasks.test {
    useJUnitPlatform()
}

