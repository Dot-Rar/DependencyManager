package com.perkelle.dev.dependencymanager.dependency.impl.nexus;

import com.perkelle.dev.dependencymanager.dependency.Dependency;
import org.bukkit.plugin.Plugin;

import java.net.MalformedURLException;
import java.net.URL;

public class MavenCentralDependency extends Dependency  {

    private final String rootUrl = "https://repo1.maven.org/maven2";
    private final String group, artifact, version;

    public MavenCentralDependency(Plugin owner, String group, String artifact, String version) {
        super(owner);

        this.group = group;
        this.artifact = artifact;
        this.version = version;
    }

    @Override
    protected URL buildUrl() throws MalformedURLException {
        String groupSlashed = String.join("/", group.split("\\."));
        String jarName = String.format("%s-%s.jar", artifact, version);
        return new URL(String.format("%s/%s/%s/%s/%s", rootUrl, groupSlashed, artifact, version, jarName));
    }

    @Override
    protected String getLocalName() {
        return String.format("%s_%s_%s.jar", group, artifact, version);
    }
}
