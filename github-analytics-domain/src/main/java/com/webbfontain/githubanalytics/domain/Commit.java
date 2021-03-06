package com.webbfontain.githubanalytics.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @author linahovanessian on 11/6/18.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Commit {

    @JsonProperty("html_url")
    private String url;
    @JsonProperty("sha")
    private String sha;
    @JsonProperty("committer")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Committer committer;

    private String email = "N/A";
    private String login = "N/A";
    private String avatarUrl = "N/A";

    @JsonProperty("commit")
    private void unpackCommitterNameFromNestedObject(Map<String, Object> commit) {
        this.email = ((Map) (commit.get( "committer" ))).get( "email" ).toString();

    }

    //some of the committers are null !!!!
    //ToDo Adding this as a workaround ! needs to be better though!
    @JsonProperty("committer")
    private void unpackCommitterAvatarFromNestedObject(Map<String, Object> committer) {
        if (committer != null) {
            this.avatarUrl = committer.get( "avatar_url" ).toString();
            this.login = committer.get( "login" ).toString();
        }

    }

    public String getLogin() {
        return login;
    }

    public Commit setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Commit setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public Committer getCommitter() {
        return committer;
    }

    public String getEmail() {
        return email;
    }

    public Commit setEmail(String email) {
        this.email = email;
        return this;
    }

    public Commit setCommitter(Committer committer) {
        if (committer == null) {//???? not sure why we have null value for committer
            this.committer = new Committer();
        } else
            this.committer = committer;
        return this;
    }


    public String getUrl() {
        return url;
    }

    public Commit setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getSha() {
        return sha;
    }

    public Commit setSha(String sha) {
        this.sha = sha;
        return this;
    }

    public String getLoginAvatar() {
        return this.login + "#" + this.avatarUrl;
    }

}
