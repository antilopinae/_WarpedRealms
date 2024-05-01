val ktorVersion: String by project
val ktxVersion: String by project
val kotlinxCoroutinesVersion: String by project
val gdxVersion: String by project
val kotlinVersion: String by project
val ktorSocketVersion: String by project
val lmlVersion: String by project

group = "adapters"
version = "unspecified"

dependencies {
    implementation("com.badlogicgames.gdx:gdx:$gdxVersion")
    implementation("com.crashinvaders.lml:gdx-kiwi:$lmlVersion")
    implementation("com.crashinvaders.lml:gdx-lml:$lmlVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("io.github.libktx:ktx-log:$ktxVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesVersion}")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-websockets:$ktorVersion")
    implementation(project(":adapters:grpc"))
    implementation(project(":adapters:rest"))
}

