package com.dotcms.rest.api.v1.sites.ruleengine;

import com.dotcms.repackage.com.fasterxml.jackson.annotation.JsonIgnore;
import com.dotcms.repackage.com.fasterxml.jackson.annotation.JsonProperty;
import com.dotcms.repackage.com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.dotcms.repackage.com.google.common.collect.ImmutableMap;
import com.dotcms.repackage.javax.validation.constraints.NotNull;
import com.dotcms.rest.api.Validated;
import com.dotcms.rest.api.v1.sites.rules.RestConditionValue;
import com.dotcms.rest.exception.BadRequestException;
import com.dotcms.rest.validation.constraints.Operator;
import com.dotmarketing.portlets.rules.model.Condition;
import java.util.Map;

import static com.dotcms.rest.validation.Preconditions.checkNotEmpty;

@JsonDeserialize(builder = RestCondition.Builder.class)
public final class RestCondition extends Validated {

    @JsonIgnore
    public final String id;

    @NotNull
    public final String name;

    @NotNull
    public final String owningGroup;

    @NotNull
    public final String conditionlet;

    @NotNull
    public final String comparison;

    public final Map<String, RestConditionValue> values;

    @NotNull
    @Operator
    public final String operator;

    public final int priority;

    private RestCondition(Builder builder) {
        id = builder.id;
        name = builder.name;
        owningGroup = builder.owningGroup;
        conditionlet = builder.conditionlet;
        comparison = builder.comparison;
        values = builder.values;
        operator = builder.operator;
        priority = builder.priority;
        checkValid();
    }

    public static final class Builder {
        @JsonProperty private String id;
        @JsonProperty private String name;
        @JsonProperty private String owningGroup;
        @JsonProperty private String conditionlet;
        @JsonProperty private String comparison;
        @JsonProperty private String operator;
        @JsonProperty private Map<String, RestConditionValue> values;
        @JsonProperty private int priority = 0;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder owningGroup(String owningGroup) {
            this.owningGroup = owningGroup;
            return this;
        }

        public Builder conditionlet(String conditionlet) {
            this.conditionlet = conditionlet != null ? conditionlet : "";
            return this;
        }

        public Builder comparison(String comparison) {
            this.comparison = comparison;
            return this;
        }

        public Builder operator(String operator) {
            this.operator = operator;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder values(Map<String, RestConditionValue> values) {
            this.values = values;
            return this;
        }

        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder from(RestCondition copy) {
            id = copy.id;
            name = copy.name;
            owningGroup = copy.owningGroup;
            conditionlet = copy.conditionlet;
            comparison = copy.comparison;
            operator = copy.operator;
            values = ImmutableMap.copyOf(copy.values);
            priority = copy.priority;
            return this;
        }

        public RestCondition build() {
            return new RestCondition(this);
        }
    }
}
