package io.codiga.plugins.jetbrains.graphql;

import com.google.common.collect.ImmutableList;
import io.codiga.api.GetProjectsQuery;
import io.codiga.api.GetRecipesForClientByShortcutQuery;
import io.codiga.api.GetRecipesForClientQuery;
import io.codiga.api.GetRecipesForClientSemanticQuery;
import io.codiga.api.type.LanguageEnumeration;
import io.codiga.plugins.jetbrains.testutils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class implements the Codiga API, which is a GraphQL API.
 * We are using apollo-android to query the API.
 * <p>
 * Apollo Android client: https://github.com/apollographql/apollo-android
 * <p>
 * This class is declared as a service to be retrieved as an application
 * service within the plugin. To retrieve it, just to
 * CodigaApi api = ApplicationManager.getApplication().getService(CodigaApi.class);
 * <p>
 * See https://plugins.jetbrains.com/docs/intellij/plugin-services.html#declaring-a-service
 */
public final class CodigaApiTest implements CodigaApi {

    public boolean isWorking() {
        return true;
    }


    /**
     * Get the username of the identified user
     *
     * @return the username of the logged in user if the API works correctly (and API keys are correct).
     */
    public Optional<String> getUsername() {
        return Optional.empty();
    }

    /**
     * Get the list of projects the user has access to.
     *
     * @return
     */
    public List<GetProjectsQuery.Project> getProjects() {
        return ImmutableList.of();
    }

    private GetRecipesForClientQuery.GetRecipesForClient generateRecipe(String name,
                                                                        String jetbrainsFormat,
                                                                        List<String> keywords,
                                                                        LanguageEnumeration language) {
      // typename
      String typename = "AssistantRecipe";
      // id
      int id = 42069;
      // imports
      List<String> imports = new ArrayList<>();
      imports.add("use std::thread;");
      // description
      String description = "Quickly spawn a thread using the std library";
      // shortcut
      String shortcut = "st";

      return new GetRecipesForClientQuery.GetRecipesForClient(
        typename,
        id,
        name,
        jetbrainsFormat,
        jetbrainsFormat,
        keywords,
        imports,
        language,
        description,
        shortcut
      );
    }

    @Override
    public List<GetRecipesForClientQuery.GetRecipesForClient> getRecipesForClient(List<String> keywords,
                                                                                  List<String> dependencies,
                                                                                  Optional<String> parameters,
                                                                                  LanguageEnumeration language,
                                                                                  String filename) {
      List<GetRecipesForClientQuery.GetRecipesForClient> recipes = new ArrayList<>();

      if (keywords.contains("testOneAutoCompleteSuggestion")) {
        recipes.add(generateRecipe("Spawn a thread",
          Constants.RECIPE_SAMPLE,
          keywords,
          language));

        return recipes;
      }

      if (keywords.contains("testMultipleAutoCompleteSuggestion")) {
        recipes.add(generateRecipe("Spawn a thread",
          Constants.RECIPE_SAMPLE,
          keywords,
          language));
        recipes.add(generateRecipe("Spawn a thread 2",
          Constants.RECIPE_SAMPLE,
          keywords,
          language));

        return recipes;
      }

      if (keywords.contains("testAcceptRecipeSuggestion")) {
        recipes.add(generateRecipe("Spawn a thread",
          Constants.RECIPE_SAMPLE,
          keywords,
          language));

        return recipes;
      }

      // The recipe here only contains `&[CODIGA_INDENT]` transform variable.
      if (keywords.contains("testIndentation")) {
        recipes.add(generateRecipe("Spawn a thread",
          "dGhyZWFkOjpzcGF3bihtb3ZlIHx8IHsKJltDT0RJR0FfSU5ERU5UXS8vIHRocmVhZCBjb2RlIGhlcmUKJltDT0RJR0FfSU5ERU5UXTQyCn0pOw==",
          keywords,
          language));

        return recipes;
      }

      /*
        The recipe in this section contains all the possible Variable Transformations at once,
        we don't create one recipe per variable.

        It doesn't contain `&[CODIGA_INDENT]`.
       */
      if (keywords.contains("testTransformer")) {
        recipes.add(generateRecipe("Spawn a thread",
          "dGhyZWFkOjpzcGF3bihtb3ZlIHx8IHsKICAvLyB0aHJlYWQgY29kZSBoZXJlCiAgJltEQVRFX0NVUlJFTlRfREFZXQogICZbREFURV9NT05USF9UV09fRElHSVRTXQogICZbREFURV9DVVJSRU5UX1NFQ09ORF9VTklYXQogICZbREFURV9DVVJSRU5UX1NFQ09ORF0KICAmW0RBVEVfQ1VSUkVOVF9NSU5VVEVdCiAgJltEQVRFX0NVUlJFTlRfSE9VUl0KICAmW0RBVEVfQ1VSUkVOVF9ZRUFSX1NIT1JUXQogICZbREFURV9DVVJSRU5UX1lFQVJdCiAgJltSQU5ET01fQkFTRV8xNl0KICAmW1JBTkRPTV9CQVNFXzEwXQogICZbREFURV9NT05USF9OQU1FX1NIT1JUXQogICZbREFURV9NT05USF9OQU1FXQogICZbREFURV9EQVlfTkFNRV9TSE9SVF0KICAmW0RBVEVfREFZX05BTUVdCiAgJltSQU5ET01fVVVJRF0KfSk7",
          keywords,
          language));

        return recipes;
      }

      /*
        The recipe in this section contains all the possible Variable Macros at once,
        we don't create one recipe per macro.

        It doesn't contain `&[CODIGA_INDENT]`.
       */
      if (keywords.contains("testMacro")) {
        recipes.add(generateRecipe("Spawn a thread",
          "dGhyZWFkOjpzcGF3bihtb3ZlIHx8IHsKICAvLyB0aHJlYWQgY29kZSBoZXJlCiAgJFNlbGVjdGVkVGV4dCQKICAkTGluZU51bWJlciQKICAkRmlsZU5hbWUkCiAgJEZpbGVOYW1lV2l0aG91dEV4dGVuc2lvbiQKICAkRmlsZURpciQKICAkRmlsZVBhdGgkCiAgJEZpbGVSZWxhdGl2ZVBhdGgkCiAgJENsaXBib2FyZENvbnRlbnQkCiAgJFByb2plY3ROYW1lJAogICRQcm9qZWN0RmlsZURpciQKfSk7",
          keywords,
          language));

        return recipes;
      }

      return recipes;
    }

    @Override
    public List<GetRecipesForClientByShortcutQuery.GetRecipesForClientByShortcut> getRecipesForClientByShotcurt(Optional<String> term, List<String> dependencies, Optional<String> parameters, LanguageEnumeration language, String filename) {
        return null;
    }

    @Override
    public Optional<Long> getRecipesForClientByShotcurtLastTimestmap(List<String> dependencies, LanguageEnumeration language) {
        return Optional.empty();
    }

    @Override
    public List<GetRecipesForClientSemanticQuery.AssistantRecipesSemanticSearch> getRecipesSemantic(Optional<String> term, List<String> dependencies, Optional<String> parameters, LanguageEnumeration language, String filename) {
        return null;
    }

    @Override
    public void recordRecipeUse(Long recipeId) {

    }
}
