import * as tsParser from "@typescript-eslint/parser";
import { defineConfig, globalIgnores } from "eslint/config";
import { createTypeScriptImportResolver } from "eslint-import-resolver-typescript";
import { importX } from "eslint-plugin-import-x";
import prettier from "eslint-plugin-prettier/recommended";
import reactHooks from "eslint-plugin-react-hooks";
import { reactRefresh } from "eslint-plugin-react-refresh";
import globals from "globals";
import { configs } from "typescript-eslint";

export default defineConfig([
  globalIgnores(["dist"]),
  {
    name: "9developers",
    extends: [
      ...configs.recommended,
      ...configs.recommendedTypeChecked,
      ...configs.stylisticTypeChecked,
      importX.flatConfigs.recommended,
      importX.flatConfigs.typescript,
      reactHooks.configs.flat.recommended,
      reactRefresh.configs.vite,
    ],
    languageOptions: {
      ecmaVersion: 2025,
      globals: globals.browser,
      parser: tsParser,
      parserOptions: {
        projectService: {
          allowDefaultProject: ["eslint.config.js", "prettier.config.js"],
        },
      },
      sourceType: "module",
    },
    linterOptions: {
      reportUnusedDisableDirectives: true,
    },
    rules: {
      "@typescript-eslint/array-type": "off",
      "@typescript-eslint/consistent-type-definitions": "off",
      "@typescript-eslint/consistent-type-imports": [
        "error",
        { prefer: "type-imports", fixStyle: "inline-type-imports" },
      ],
      "@typescript-eslint/no-unused-vars": [
        "warn",
        { argsIgnorePattern: "^_" },
      ],
      "@typescript-eslint/require-await": "off",
      "@typescript-eslint/no-misused-promises": [
        "error",
        { checksVoidReturn: { attributes: false } },
      ],
      "import-x/order": [
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
      "import-x/internal-regex": "^~/",
      "import-x/resolver-next": [
        createTypeScriptImportResolver({ project: "./tsconfig.app.json" }),
      ],
    },
  },
  prettier,
]);
