package org.kondrak.shared.config;

public class Configuration {

    private Long assignmentId;
    private Long configurationId;
    private ConfigScope scope;
    private String assignedKey;
    private ConfigType configType;
    private ConfigDatatype configDatatype;
    private String configValue;

    public Configuration() {
        // mybatis constructor
    }

    public Configuration(Long assignmentId, Long configurationId, ConfigScope scope, String assignedKey,
                         ConfigType configType, ConfigDatatype configDatatype, String configValue) {
        this.assignmentId = assignmentId;
        this.configurationId = configurationId;
        this.scope = scope;
        this.assignedKey = assignedKey;
        this.configType = configType;
        this.configDatatype = configDatatype;
        this.configValue = configValue;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public Long getConfigurationId() {
        return configurationId;
    }

    public ConfigScope getScope() {
        return scope;
    }

    public String getAssignedKey() {
        return assignedKey;
    }

    public ConfigType getConfigType() {
        return configType;
    }

    public ConfigDatatype getConfigDatatype() {
        return configDatatype;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public void setConfigurationId(Long configurationId) {
        this.configurationId = configurationId;
    }

    public void setScope(ConfigScope scope) {
        this.scope = scope;
    }

    public void setAssignedKey(String assignedKey) {
        this.assignedKey = assignedKey;
    }

    public void setConfigType(ConfigType configType) {
        this.configType = configType;
    }

    public void setConfigDatatype(ConfigDatatype configDatatype) {
        this.configDatatype = configDatatype;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Configuration that = (Configuration) o;

        if (!assignmentId.equals(that.assignmentId)) return false;
        if (!configurationId.equals(that.configurationId)) return false;
        if (scope != that.scope) return false;
        if (!assignedKey.equals(that.assignedKey)) return false;
        if (configType != that.configType) return false;
        if (configDatatype != that.configDatatype) return false;
        return configValue.equals(that.configValue);
    }

    @Override
    public int hashCode() {
        int result = assignmentId.hashCode();
        result = 31 * result + configurationId.hashCode();
        result = 31 * result + scope.hashCode();
        result = 31 * result + assignedKey.hashCode();
        result = 31 * result + configType.hashCode();
        result = 31 * result + configDatatype.hashCode();
        result = 31 * result + configValue.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "assignmentId=" + assignmentId +
                ", configurationId=" + configurationId +
                ", scope=" + scope +
                ", assignedKey='" + assignedKey + '\'' +
                ", configType=" + configType +
                ", configDatatype=" + configDatatype +
                ", configValue=" + configValue +
                '}';
    }
}
