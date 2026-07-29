import js from "@eslint/js";
import globals from "globals";
import prettier from "eslint-plugin-prettier/recommended";
import importPlugin from "eslint-plugin-import";
import reactHooks from "eslint-plugin-react-hooks";
import reactRefresh from "eslint-plugin-react-refresh";
import { defineConfig, globalIgnores } from "eslint/config";
import tseslint from "typescript-eslint";

export default defineConfig([
  globalIgnores(["dist"]),
  {
    name: "9developers",
    files: ["**/*.{ts,tsx}"],
    extends: [
      importPlugin.flatConfigs.recommended,
      js.configs.recommended,
      reactHooks.configs.flat.recommended,
      reactRefresh.configs.vite,
      tseslint.configs.recommendedTypeChecked,
      tseslint.configs.stylisticTypeChecked,
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
      "@typescript-eslint/no-unused-vars": [
        "error",
        { argsIgnorePattern: "^_" },
      ],
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
  prettier,
]);
