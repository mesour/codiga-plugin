package io.codiga.plugins.jetbrains.assistant.transformers;

import io.codiga.plugins.jetbrains.model.CodingAssistantContext;

public class VariableTransformerFilenameNoExtension implements VariableTransformer {

  @Override
  public String transform(String code, CodingAssistantContext CodigaTransformationContext){
    final String filename = CodigaTransformationContext.virtualFile.getNameWithoutExtension();
    return code.replace(CodingAssistantContext.GET_FILENAME_NO_EXT, filename);
  }
}
