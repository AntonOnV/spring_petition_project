package org.example.openapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VotesListResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-03T02:53:03.860059+02:00[Europe/Kiev]")
public class VotesListResponse   {
  @JsonProperty("petitionId")
  private Long petitionId = null;

  @JsonProperty("votes")
  private Integer votes;

  public VotesListResponse petitionId(Long petitionId) {
    this.petitionId = petitionId;
    return this;
  }

  /**
   * Get petitionId
   * @return petitionId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getPetitionId() {
    return petitionId;
  }

  public void setPetitionId(Long petitionId) {
    this.petitionId = petitionId;
  }

  public VotesListResponse votes(Integer votes) {
    this.votes = votes;
    return this;
  }

  /**
   * Get votes
   * @return votes
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getVotes() {
    return votes;
  }

  public void setVotes(Integer votes) {
    this.votes = votes;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VotesListResponse votesListResponse = (VotesListResponse) o;
    return Objects.equals(this.petitionId, votesListResponse.petitionId) &&
        Objects.equals(this.votes, votesListResponse.votes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(petitionId, votes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VotesListResponse {\n");
    
    sb.append("    petitionId: ").append(toIndentedString(petitionId)).append("\n");
    sb.append("    votes: ").append(toIndentedString(votes)).append("\n");
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

