import type { ReactNode } from "react";
import { Link } from "react-router";

function PluginPaths({
  paths,
  plugin,
}: Readonly<{ paths: string[]; plugin: string }>): ReactNode {
  return (
    <>
      <h2><Link to={`/webserver/plugins/${plugin}`}>{plugin}</Link></h2>

      {paths.map((path) => (
        <Link key={path} id={path} to={path} />
      ))}
    </>
  );
}

export default PluginPaths;
