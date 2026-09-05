@file:Suppress("UnstableApiUsage")

plugins {
    id("net.fabricmc.fabric-loom")
    id("dev.kikugie.postprocess.jsonlang")
    id("me.modmuss50.mod-publish-plugin")
    id("maven-publish")
}

val minecraft = stonecutter.current.version
val mcVersion = stonecutter.current.project.substringBeforeLast('-')

tasks.named<ProcessResources>("processResources") {
    fun prop(name: String) = project.property(name) as String

    val props = HashMap<String, String>().apply {
        this["version"] = prop("mod.version") + "+" + prop("deps.minecraft")
        this["minecraft"] = prop("deps.minecraft")
    }

    filesMatching(listOf("fabric.mod.json", "META-INF/neoforge.mods.toml", "META-INF/mods.toml")) {
        expand(props)
    }

}

tasks.named("processResources") {
    dependsOn(":${stonecutter.current.project}:stonecutterGenerate")
}

version = "${property("mod.version")}+${property("deps.minecraft")}-fabric"
base.archivesName = property("mod.id") as String

//loom {
//    accessWidenerPath = rootProject.file("src/main/resources/${property("mod.id")}.accesswidener")
//}

jsonlang {
    languageDirectories = listOf("assets/${property("mod.id")}/lang")
    prettyPrint = true
}

repositories {
    mavenLocal()
    exclusiveContent {
        forRepository {
            maven {
                name = "shedaniel (Cloth Config)"
                url = uri("https://maven.shedaniel.me/")
            }
        }
        filter {
            includeGroupAndSubgroups("me.shedaniel")

        }
    }
    exclusiveContent {
        forRepository {
            maven {
                name = "Terraformers (Mod Menu)"
                url = uri("https://maven.terraformersmc.com/releases/")
            }
        }
        filter {
            includeGroupAndSubgroups("com.terraformersmc")
            includeGroupAndSubgroups("dev.emi")
        }
    }
    exclusiveContent {
        forRepository {
            maven {
                name = "Sisby Maven"
                url = uri("https://repo.sleeping.town/")
            }
        }
        filter {
            includeGroupAndSubgroups("folk.sisby")
        }
    }
    exclusiveContent {
        forRepository {
            maven {
                name = "Xander Maven"
                url = uri("https://maven.isxander.dev/releases")
            }
        }
        filter {
            includeGroupAndSubgroups("org.quiltmc.parsers")
        }
    }
    exclusiveContent {
        forRepository {
            maven {
                name = "REI Maven"
                url = uri("https://maven.architectury.dev")
            }
        }
        filter {
            includeGroupAndSubgroups("dev.architectury")
        }
    }
    exclusiveContent {
        forRepository {
            maven {
                name = "Fuzs Mod Resources"
                url = uri("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
            }
        }
        filter {
            includeGroupAndSubgroups("fuzs")
        }
    }
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = uri("https://api.modrinth.com/maven")
            }
        }
        filter {
            includeGroupAndSubgroups("maven.modrinth")
        }
    }
    maven {
        name = "JEI - Jared's maven"
        url = uri("https://maven.blamejared.com/")
        content {
            includeGroup("mezz.jei")
        }
    }
    maven {
        name = "JEI - fallback maven"
        url = uri("https://modmaven.dev/")
        content {
            includeGroup("mezz.jei")
        }
    }
    exclusiveContent {
        forRepository {
            maven {
                name = "Greenhouse Maven"
                url = uri("https://maven.greenhouse.lgbt/releases/")
            }
        }
        filter {
            includeGroup("vectorwing")
        }
    }
    exclusiveContent {
        forRepository {
            maven {
                name = "Greenhouse Maven"
                url = uri("https://jitpack.io")
            }
        }
        filter {
            includeGroup("com.github.Chocohead")
        }
    }
    maven {
        name = "Cassian's Maven"
        url = uri("https://maven.cassian.cc")
        content {
            includeGroupAndSubgroups("cc.cassian")
        }
    }
}

dependencies {
    minecraft("com.mojang:minecraft:${property("deps.minecraft")}")
    implementation("net.fabricmc:fabric-loader:${property("deps.fabric-loader")}")

    implementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric-api")}")

    implementation("com.terraformersmc:modmenu:${property("deps.modmenu")}")

    implementation("folk.sisby:kaleido-config:${property("deps.kaleido")}")
    include("folk.sisby:kaleido-config:${property("deps.kaleido")}")

    compileOnly("me.shedaniel.cloth:cloth-config-neoforge:19.0.147")
    compileOnly("dev.isxander:yet-another-config-lib:${property("deps.yacl")}") {
        isTransitive = false
    }
    // Recipe Viewers
    compileOnly("cc.cassian.rrv:reliable-recipe-viewer-fabric:${property("deps.rrv")}")
    runtimeOnly("cc.cassian.rrv:reliable-recipe-viewer-fabric:${property("deps.rrv")}")
    compileOnly("me.shedaniel:RoughlyEnoughItems-api-neoforge:${property("deps.rei")}")
    compileOnly("me.shedaniel:RoughlyEnoughItems-default-plugin-neoforge:${property("deps.rei")}")
    compileOnly("mezz.jei:jei-26.2-fabric:${property("deps.jei")}")

    compileOnly("fuzs.iteminteractions:iteminteractions-fabric:${property("deps.iteminteractions")}")
    // Development QOL
//    runtimeOnly("cc.cassian.item-descriptions:item-descriptions-fabric:${property("deps.item_descriptions")}") {
//        isTransitive = false
//    }

}

configurations.all {
    resolutionStrategy {
        force("net.fabricmc:fabric-loader:${property("deps.fabric-loader")}")
    }
}

stonecutter {
    replacements.string {
        direction = eval(current.version, ">1.21")
        replace("ResourceLocation", "Identifier")
    }
}

fabricApi {
    configureDataGeneration() {
        outputDirectory = file("$rootDir/src/main/generated")
        client = true
    }
}

tasks {
    processResources {
        exclude("**/neoforge.mods.toml", "**/mods.toml")
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        from(jar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
        dependsOn("build")
    }
}

loom.runs.named("server") {
    isIdeConfigGenerated = false
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

val additionalVersionsStr = findProperty("publish.additionalVersions") as String?
val additionalVersions: List<String> = additionalVersionsStr
    ?.split(",")
    ?.map { it.trim() }
    ?.filter { it.isNotEmpty() }
    ?: emptyList()

publishMods {
    file = tasks.jar.map { it.archiveFile.get() }
    additionalFiles.from(tasks.named<org.gradle.jvm.tasks.Jar>("sourcesJar").map { it.archiveFile.get() })

    // one of BETA, ALPHA, STABLE
    type = BETA
    displayName = "${property("mod.name")} ${property("mod.version")} for ${stonecutter.current.version} Fabric"
    version = "${property("mod.version")}+${property("deps.minecraft")}-fabric"
    changelog = provider { rootProject.file("CHANGELOG-LATEST.md").readText() }
    modLoaders.add("fabric")

    modrinth {
        projectId = property("publish.modrinth") as String
        accessToken = env.MODRINTH_API_KEY.orNull()
        minecraftVersions.add(property("deps.minecraft").toString())
        minecraftVersions.addAll(additionalVersions)
        requires("fabric-api")
        optional("mcqoy")
    }

    curseforge {
        projectId = property("publish.curseforge") as String
        accessToken = env.CURSEFORGE_API_KEY.orNull()
        minecraftVersions.add(property("deps.minecraft").toString())
        minecraftVersions.addAll(additionalVersions)
        requires("fabric-api")
        client = true
        server = true
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "cc.cassian.bigger_fish"
            artifactId = "bigger-fish-fabric"
            version = "${property("mod.version")}+${property("deps.minecraft")}"

            from(components["java"])
        }
    }
}