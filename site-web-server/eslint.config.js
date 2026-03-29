import js from "@eslint/js";
import globals from "globals";
import reactHooks from "eslint-plugin-react-hooks";
import reactRefresh from "eslint-plugin-react-refresh";
import tseslint from "typescript-eslint";
import { defineConfig, globalIgnores } from "eslint/config";
import importPlugin from "eslint-plugin-import";

export default defineConfig([
  globalIgnores(["dist"]),
  {
    files: ["**/*.{ts,tsx}"],
    extends: [
      importPlugin.flatConfigs.recommended,
      js.configs.recommended,
      reactHooks.configs.flat.recommended,
      reactRefresh.configs.vite,
      ...tseslint.configs.recommendedTypeChecked,
      ...tseslint.configs.stylisticTypeChecked,
    ],
    languageOptions: {
      ecmaVersion: 2020,
      globals: globals.browser,
      parserOptions: {
        projectService: true,
      },
    },
    linterOptions: {
      reportUnusedDisableDirectives: true,
    },
    rules: {
      "import/order": [
        "error",
        {
          alphabetize: {
            order: "asc",
            caseInsensitive: true,
          },
          groups: [
            // Imports of builtins are first
            "builtin",
            // Then external imports
            "external",
            // Then internal imports
            "internal",
            // Then sibling and parent imports. They can be mingled together
            ["sibling", "parent"],
            // Then index file imports
            "index",
            // Then any arcane TypeScript imports
            "object",
            // Then the omitted imports: type, unknown
          ],
          named: true,
          "newlines-between": "always",
        },
      ],
    },
    settings: {
      "import/internal-regex": "^~/",
      "import/resolver": {
        typescript: {},
      },
    },
  },
]);
