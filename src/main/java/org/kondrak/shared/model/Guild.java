package org.kondrak.shared.model;

public class Guild {
    private String guildId;
    private String guildName;

    public Guild() {
        // empty constructor for myBatis
    }

    public Guild(String guildId, String guildName) {
        this.guildId = guildId;
        this.guildName = guildName;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guild guild = (Guild) o;

        if (guildId != null ? !guildId.equals(guild.guildId) : guild.guildId != null) return false;
        return guildName != null ? guildName.equals(guild.guildName) : guild.guildName == null;
    }

    @Override
    public int hashCode() {
        int result = guildId != null ? guildId.hashCode() : 0;
        result = 31 * result + (guildName != null ? guildName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Guild{" +
                "guildId='" + guildId + '\'' +
                ", guildName='" + guildName + '\'' +
                '}';
    }
}
