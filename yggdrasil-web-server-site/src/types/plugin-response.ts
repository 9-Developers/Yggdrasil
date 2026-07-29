/**
 * Plugin response.
 */
export type PluginResponse = Readonly<{
  /** HTTP paths registered to the plugin. */
  paths: string[];
  /** Resources registered to the plugin. */
  resources: string[];
}>;
