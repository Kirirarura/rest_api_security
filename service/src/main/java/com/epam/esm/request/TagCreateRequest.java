package com.epam.esm.request;

import com.epam.esm.validation.tag.Name;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class TagCreateRequest {
  @Name
  String name;
}
