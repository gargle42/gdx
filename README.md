gdx
===

#Spielereien mit der GDX-Lib

Jens und Andre Willkowsky, 10.2014

    mvn archetype:generate
        -DarchetypeRepository=local
        -DarchetypeRepository=$HOME/.m2/repository
        -DarchetypeGroupId=com.badlogic.gdx
        -DarchetypeArtifactId=gdx-archetype
        -DarchetypeVersion=1.2.0
        -DgroupId=de.willkowsky
        -DartifactId=andresGame2
        -Dversion=0.0.1
        -Dpackage=de.willkowsky
        -DJavaGameClassName=AndresGameClass


Auch prima hat folgende einfache app funktioniert:

vorher noch

    export ANDROID_HOME=~/mount/work/adt-bundle-linux-x86_64-20140702/sdk/
    export JAVA_HOME=~/mount/work/jdk1.8.0_11/;
    export PATH=~/mount/work/apache-maven-3.2.3/bin:${JAVA_HOME}/bin:$PATH; mvn -version 2>&1 | \
    grep -E "Java version|Apache Maven"
    
    mvn archetype:generate -DarchetypeArtifactId=android-quickstart
        -DarchetypeGroupId=de.akquinet.android.archetypes -DarchetypeVersion=1.1.0 -DgroupId=your.company
        -DartifactId=my-android-application3

Installiert und _startet_ die app auf dem Device

    mvn clean install -Pdesktop

So werden Blenderdateien in g3db konvertiert:

    export LD_LIBRARY_PATH=~/mount/work/fbx
    ~/mount/work/fbx/fbx-conv-lin64 -f -v ~/Desktop/blender/monkey-heile.fbx

