package com.project.resourceserver.constants.enums;

public enum ContactPref {
    NONE("NONE"),
    CALL("CALL"),
    TEXT("TEXT"),
    TEXT_OR_CALL("TEXT_OR_CALL");

    public final String label;

    private ContactPref(String label) {
      this.label = label;
    }
  }