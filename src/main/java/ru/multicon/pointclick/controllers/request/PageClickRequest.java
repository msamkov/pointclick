package ru.multicon.pointclick.controllers.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class PageClickRequest {

    @NotNull
    Long userId;

    @NotNull
    Long pageId;
}
