package com.assessment.work.grandKapital_api.models;

import java.net.URI;
import java.util.Objects;
import com.assessment.work.grandKapital_api.models.PageElementsInner;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Page
 */

@Schema(name = "Page", description = "Page")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-24T14:46:25.740225500+03:00[Europe/Minsk]")
public class Page {

  @JsonProperty("number")
  private Integer number;

  @JsonProperty("total")
  private Integer total;

  @JsonProperty("elementsTotal")
  private Long elementsTotal;

  @JsonProperty("elements")
  @Valid
  private List<PageElementsInner> elements = null;

  public Page number(Integer number) {
    this.number = number;
    return this;
  }

  /**
   * Page number
   * @return number
  */
  
  @Schema(name = "number", description = "Page number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Page total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Amount of pages
   * @return total
  */
  
  @Schema(name = "total", description = "Amount of pages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Page elementsTotal(Long elementsTotal) {
    this.elementsTotal = elementsTotal;
    return this;
  }

  /**
   * Total amount of elements on all pages
   * @return elementsTotal
  */
  
  @Schema(name = "elementsTotal", description = "Total amount of elements on all pages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getElementsTotal() {
    return elementsTotal;
  }

  public void setElementsTotal(Long elementsTotal) {
    this.elementsTotal = elementsTotal;
  }

  public Page elements(List<PageElementsInner> elements) {
    this.elements = elements;
    return this;
  }

  public Page addElementsItem(PageElementsInner elementsItem) {
    if (this.elements == null) {
      this.elements = new ArrayList<>();
    }
    this.elements.add(elementsItem);
    return this;
  }

  /**
   * Get elements
   * @return elements
  */
  @Valid 
  @Schema(name = "elements", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public List<PageElementsInner> getElements() {
    return elements;
  }

  public void setElements(List<PageElementsInner> elements) {
    this.elements = elements;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Page page = (Page) o;
    return Objects.equals(this.number, page.number) &&
        Objects.equals(this.total, page.total) &&
        Objects.equals(this.elementsTotal, page.elementsTotal) &&
        Objects.equals(this.elements, page.elements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, total, elementsTotal, elements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Page {\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    elementsTotal: ").append(toIndentedString(elementsTotal)).append("\n");
    sb.append("    elements: ").append(toIndentedString(elements)).append("\n");
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

