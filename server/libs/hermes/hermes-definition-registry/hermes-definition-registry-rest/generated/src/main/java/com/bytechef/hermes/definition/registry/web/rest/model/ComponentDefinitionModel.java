package com.bytechef.hermes.definition.registry.web.rest.model;

import java.net.URI;
import java.util.Objects;
import com.bytechef.hermes.definition.registry.web.rest.model.ActionDefinitionBasicModel;
import com.bytechef.hermes.definition.registry.web.rest.model.ConnectionDefinitionBasicModel;
import com.bytechef.hermes.definition.registry.web.rest.model.DisplayModel;
import com.bytechef.hermes.definition.registry.web.rest.model.ResourcesModel;
import com.bytechef.hermes.definition.registry.web.rest.model.TriggerDefinitionBasicModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * A component contains a set of reusable code(actions) that accomplish specific tasks, triggers and connections if there is a need for a connection to an outside service.
 */

@Schema(name = "ComponentDefinition", description = "A component contains a set of reusable code(actions) that accomplish specific tasks, triggers and connections if there is a need for a connection to an outside service.")
@JsonTypeName("ComponentDefinition")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-15T19:47:32.550589+02:00[Europe/Zagreb]")
public class ComponentDefinitionModel {

  @Valid
  private List<@Valid ActionDefinitionBasicModel> actions;

  private ConnectionDefinitionBasicModel connection;

  private DisplayModel display;

  private String name;

  private ResourcesModel resources;

  @Valid
  private List<@Valid TriggerDefinitionBasicModel> triggers;

  private Integer version;

  /**
   * Default constructor
   * @deprecated Use {@link ComponentDefinitionModel#ComponentDefinitionModel(DisplayModel, String, Integer)}
   */
  @Deprecated
  public ComponentDefinitionModel() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ComponentDefinitionModel(DisplayModel display, String name, Integer version) {
    this.display = display;
    this.name = name;
    this.version = version;
  }

  public ComponentDefinitionModel actions(List<@Valid ActionDefinitionBasicModel> actions) {
    this.actions = actions;
    return this;
  }

  public ComponentDefinitionModel addActionsItem(ActionDefinitionBasicModel actionsItem) {
    if (this.actions == null) {
      this.actions = new ArrayList<>();
    }
    this.actions.add(actionsItem);
    return this;
  }

  /**
   * The list of all available actions the component can perform.
   * @return actions
  */
  @Valid 
  @Schema(name = "actions", description = "The list of all available actions the component can perform.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("actions")
  public List<@Valid ActionDefinitionBasicModel> getActions() {
    return actions;
  }

  public void setActions(List<@Valid ActionDefinitionBasicModel> actions) {
    this.actions = actions;
  }

  public ComponentDefinitionModel connection(ConnectionDefinitionBasicModel connection) {
    this.connection = connection;
    return this;
  }

  /**
   * Get connection
   * @return connection
  */
  @Valid 
  @Schema(name = "connection", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("connection")
  public ConnectionDefinitionBasicModel getConnection() {
    return connection;
  }

  public void setConnection(ConnectionDefinitionBasicModel connection) {
    this.connection = connection;
  }

  public ComponentDefinitionModel display(DisplayModel display) {
    this.display = display;
    return this;
  }

  /**
   * Get display
   * @return display
  */
  @NotNull @Valid 
  @Schema(name = "display", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("display")
  public DisplayModel getDisplay() {
    return display;
  }

  public void setDisplay(DisplayModel display) {
    this.display = display;
  }

  public ComponentDefinitionModel name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name.
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "The name.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ComponentDefinitionModel resources(ResourcesModel resources) {
    this.resources = resources;
    return this;
  }

  /**
   * Get resources
   * @return resources
  */
  @Valid 
  @Schema(name = "resources", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("resources")
  public ResourcesModel getResources() {
    return resources;
  }

  public void setResources(ResourcesModel resources) {
    this.resources = resources;
  }

  public ComponentDefinitionModel triggers(List<@Valid TriggerDefinitionBasicModel> triggers) {
    this.triggers = triggers;
    return this;
  }

  public ComponentDefinitionModel addTriggersItem(TriggerDefinitionBasicModel triggersItem) {
    if (this.triggers == null) {
      this.triggers = new ArrayList<>();
    }
    this.triggers.add(triggersItem);
    return this;
  }

  /**
   * The list of all available triggers the component can perform.
   * @return triggers
  */
  @Valid 
  @Schema(name = "triggers", description = "The list of all available triggers the component can perform.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("triggers")
  public List<@Valid TriggerDefinitionBasicModel> getTriggers() {
    return triggers;
  }

  public void setTriggers(List<@Valid TriggerDefinitionBasicModel> triggers) {
    this.triggers = triggers;
  }

  public ComponentDefinitionModel version(Integer version) {
    this.version = version;
    return this;
  }

  /**
   * The version of a component.
   * @return version
  */
  @NotNull 
  @Schema(name = "version", description = "The version of a component.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("version")
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComponentDefinitionModel componentDefinition = (ComponentDefinitionModel) o;
    return Objects.equals(this.actions, componentDefinition.actions) &&
        Objects.equals(this.connection, componentDefinition.connection) &&
        Objects.equals(this.display, componentDefinition.display) &&
        Objects.equals(this.name, componentDefinition.name) &&
        Objects.equals(this.resources, componentDefinition.resources) &&
        Objects.equals(this.triggers, componentDefinition.triggers) &&
        Objects.equals(this.version, componentDefinition.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(actions, connection, display, name, resources, triggers, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComponentDefinitionModel {\n");
    sb.append("    actions: ").append(toIndentedString(actions)).append("\n");
    sb.append("    connection: ").append(toIndentedString(connection)).append("\n");
    sb.append("    display: ").append(toIndentedString(display)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    resources: ").append(toIndentedString(resources)).append("\n");
    sb.append("    triggers: ").append(toIndentedString(triggers)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

