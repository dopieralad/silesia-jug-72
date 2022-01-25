import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    java
    kotlin("jvm") version "1.6.10"
    scala
}

repositories {
    mavenCentral()
}

dependencies {

    // Kotlin
    implementation(kotlin("stdlib"))

    // Scala
    val scalaVersion: String by project
    implementation("org.scala-lang:scala-library:$scalaVersion")

    // Coroutines
    val coroutinesVersion: String by project
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    // Project Reactor
    val projectReactorVersion: String by project
    implementation(platform("io.projectreactor:reactor-bom:2020.0.15"))
    implementation("io.projectreactor:reactor-core")

    // RxJava
    val rxJavaVersion: String by project
    implementation("io.reactivex.rxjava3:rxjava:$rxJavaVersion")

    // Arrow
    val arrowVersion: String by project
    implementation("io.arrow-kt:arrow-core-data:$arrowVersion")
    implementation("io.arrow-kt:arrow-fx:$arrowVersion")

    // Monix
    val monixVersion: String by project
    implementation("io.monix:monix_2.13:$monixVersion")

    testImplementation(kotlin("test"))
}

application {
    mainClass.set("MainKt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.test {
    useJUnitPlatform()
}

tasks.wrapper {
    gradleVersion = "7.3.3"
    distributionType = Wrapper.DistributionType.ALL
}
