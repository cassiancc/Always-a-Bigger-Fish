@file:Suppress("UnstableApiUsage")

plugins {
    id("net.fabricmc.fabric-loom-remap")
    id("dev.kikugie.postprocess.jsonlang")
    id("me.modmuss50.mod-publish-plugin")
}

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
                name = "Parchment Mappings"
                url = uri("https://maven.parchmentmc.org")
            }
        }
        filter {
            includeGroupAndSubgroups("org.parchmentmc")
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
            includeGroupAndSubgroups("dev.isxander")
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
}

dependencies {
    minecraft("com.mojang:minecraft:${property("deps.minecraft")}")
    mappings(loom.layered {
        officialMojangMappings()
        if (hasProperty("deps.parchment"))
            parchment("org.parchmentmc.data:parchment-${property("deps.parchment")}@zip")
    })
    modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric-loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric-api")}")
    // Kaleido
    implementation("folk.sisby:kaleido-config:${property("deps.kaleido")}")
    include("folk.sisby:kaleido-config:${property("deps.kaleido")}")
    modImplementation( "maven.modrinth:mcqoy:adCKjC4q")

    // Cloth Config
    if (hasProperty("deps.cloth_config")) {
        modImplementation("me.shedaniel.cloth:cloth-config-fabric:${property("deps.cloth_config")}")
    } else {
        modCompileOnly("me.shedaniel.cloth:cloth-config-fabric:19.0.147")
    }
    // Mod Menu
    if (hasProperty("deps.modmenu"))
        modApi("com.terraformersmc:modmenu:${property("deps.modmenu")}")
    else {
        modCompileOnly("com.terraformersmc:modmenu:15.0.0-beta.3")
    }

    // Farmer's Delight
    modImplementation("vectorwing:FarmersDelight:${property("deps.fd")}+refabricated") {
        exclude(group = "net.fabricmc")
        exclude(group = "me.shedaniel")
    }

    // Optional compat
    modApi("fuzs.iteminteractions:iteminteractions-fabric:${property("deps.iteminteractions")}")
    modImplementation("maven.modrinth:compost:${property("deps.compost")}")
    // Development QOL
    modLocalRuntime("cc.cassian.item-descriptions:item-descriptions-fabric:${property("deps.item_descriptions")}")

    // Recipe Viewers
    if (hasProperty("deps.eiv")) {
        modImplementation("maven.modrinth:eiv:${property("deps.eiv")}-fabric")
    }
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-api:${property("deps.rei")}")
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-default-plugin:${property("deps.rei")}")
    modCompileOnly("mezz.jei:jei-${property("deps.minecraft")}-fabric-api:${property("deps.jei")}")
    if (hasProperty("deps.emi")) {
        modImplementation("maven.modrinth:emi:${property("deps.emi")}+${property("deps.minecraft")}+fabric")
    }

    val modules = listOf("transitive-access-wideners-v1", "registry-sync-v0", "resource-loader-v0")
    for (it in modules) modImplementation(fabricApi.module("fabric-$it", property("deps.fabric-api") as String))
}

//fabricApi {
//    configureDataGeneration() {
//        outputDirectory = file("$rootDir/src/main/generated")
//        client = true
//    }
//}

tasks {
    processResources {
        exclude("**/neoforge.mods.toml", "**/mods.toml")
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        from(remapJar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
        dependsOn("build")
    }
}

java {
    withSourcesJar()
    val javaCompat = if (stonecutter.eval(stonecutter.current.version, ">=1.21")) {
        JavaVersion.VERSION_21
    } else {
        JavaVersion.VERSION_17
    }
    sourceCompatibility = javaCompat
    targetCompatibility = javaCompat
}

val additionalVersionsStr = findProperty("publish.additionalVersions") as String?
val additionalVersions: List<String> = additionalVersionsStr
    ?.split(",")
    ?.map { it.trim() }
    ?.filter { it.isNotEmpty() }
    ?: emptyList()

publishMods {
    file = tasks.remapJar.map { it.archiveFile.get() }
    additionalFiles.from(tasks.remapSourcesJar.map { it.archiveFile.get() })

    type = BETA
    displayName = "${property("mod.name")} ${property("mod.version")} for ${stonecutter.current.version} Fabric"
    version = "${property("mod.version")}+${property("deps.minecraft")}-fabric"
    changelog = provider { rootProject.file("CHANGELOG-LATEST.md").readText() }
    modLoaders.add("fabric")

    modrinth {
        projectId = property("publish.modrinth") as String
        accessToken = env.MODRINTH_API_KEY.orNull()
        minecraftVersions.add(stonecutter.current.version)
        minecraftVersions.addAll(additionalVersions)
        requires("fabric-api")
        requires("mcqoy")

    }

    curseforge {
        projectId = property("publish.curseforge") as String
        accessToken = env.CURSEFORGE_API_KEY.orNull()
        minecraftVersions.add(stonecutter.current.version)
        minecraftVersions.addAll(additionalVersions)
        requires("fabric-api")
    }
}