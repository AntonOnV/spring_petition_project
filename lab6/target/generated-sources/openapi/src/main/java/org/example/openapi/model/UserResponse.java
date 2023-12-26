package org.example.openapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-03T02:53:03.860059+02:00[Europe/Kiev]")
public class UserResponse   {
  @JsonProperty("userId")
  private Long userId = null;

  @JsonProperty("votes")
  @Valid
  private List<Long> votes = new ArrayList<>();

  public UserResponse userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public UserResponse votes(List<Long> votes) {
    this.votes = votes;
    return this;
  }

  public UserResponse addVotesItem(Long votesItem) {
    this.votes.add(votesItem);
    return this;
  }

  /**
   * Get votes
   * @return votes
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public List<Long> getVotes() {
    return votes;
  }

  public void setVotes(List<Long> votes) {
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
    UserResponse userResponse = (UserResponse) o;
    return Objects.equals(this.userId, userResponse.userId) &&
        Objects.equals(this.votes, userResponse.votes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, votes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserResponse {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

