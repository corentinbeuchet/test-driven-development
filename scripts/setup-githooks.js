import { execSync } from "node:child_process";
import { chmodSync, existsSync, mkdirSync } from "node:fs";

function run(cmd) {
  execSync(cmd, { stdio: "inherit" });
}

function ensureDir(path) {
  mkdirSync(path, { recursive: true });
}

try {
  // Verify we are inside a git repository
  run("git rev-parse --is-inside-work-tree");

  // Ensure expected directories exist (useful on fresh clones)
  ensureDir(".githooks");
  ensureDir("scripts");

  // Configure git to use versioned hooks folder
  run('git config core.hooksPath ".githooks"');

  // Best-effort: make hooks executable (mainly for macOS/Linux/WSL/Git Bash)
  for (const hook of [".githooks/commit-msg", ".githooks/pre-push"]) {
    if (existsSync(hook)) {
      try {
        chmodSync(hook, 0o755);
      } catch {
        // On Windows or some FS, chmod may not apply; that's OK.
      }
    }
  }

  console.log("OK: Git hooks enabled (core.hooksPath = .githooks).");
} catch {
  console.error(
    "ERROR: Failed to set up git hooks. Ensure Git is installed and you're running this in a Git repository."
  );
  process.exit(1);
}
