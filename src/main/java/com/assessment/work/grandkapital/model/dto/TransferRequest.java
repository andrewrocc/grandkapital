package com.assessment.work.grandkapital.model.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class TransferRequest {

    @JsonProperty("toUserId")
    private Long toUserId;

    @JsonProperty("amount")
    private java.math.BigDecimal amount;

    public TransferRequest toUserId(Long toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    /**
     * destination user
     *
     * @return toUserId
     */
    @NotNull
    @Schema(name = "toUserId", example = "101", description = "destination user", requiredMode = Schema.RequiredMode.REQUIRED)
    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public TransferRequest amount(java.math.BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    /**
     * amount of money to transfer
     *
     * @return amount
     */
    @NotNull
    @Schema(name = "amount", example = "99.9", description = "amount of money to transfer", requiredMode = Schema.RequiredMode.REQUIRED)
    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransferRequest transferRequest = (TransferRequest) o;
        return Objects.equals(this.toUserId, transferRequest.toUserId) &&
                Objects.equals(this.amount, transferRequest.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toUserId, amount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TransferRequest {\n");
        sb.append("    toUserId: ").append(toIndentedString(toUserId)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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