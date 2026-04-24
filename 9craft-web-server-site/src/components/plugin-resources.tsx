import type { ReactNode } from "react";
import { Link } from "react-router";

export function PluginResources({
  plugin,
  resources,
}: Readonly<{ plugin: string; resources: string[] }>): ReactNode {
  return (
    <>
      <h2>
        <Link to={`/webserver/plugins/${plugin}`}>{plugin}</Link>
      </h2>

      {resources.map((resource) => (
        <Link
          key={resource}
          id={resource}
          to={`/webserver/status/${plugin}/${resource}`}
        />
      ))}
    </>
  );
}
