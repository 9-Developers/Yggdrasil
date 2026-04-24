import { type ReactNode } from "react";

export function AngleDownIcon({
  className,
}: Readonly<{ className?: string }>): ReactNode {
  return (
    <svg
      className={className}
      fill="none"
      stroke="currentColor"
      strokeWidth={0.08}
      strokeLinecap="round"
      strokeLinejoin="round"
      width={16}
      height={16}
      viewBox="0 0 0.96 0.96"
      xmlns="http://www.w3.org/2000/svg"
      aria-hidden="true"
    >
      <path d="M0.74 0.36 0.5 0.6 0.26 0.36" />
    </svg>
  );
}
