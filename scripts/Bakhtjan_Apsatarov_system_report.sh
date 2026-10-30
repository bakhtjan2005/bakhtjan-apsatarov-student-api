#!/usr/bin/env bash
# System monitoring script — Bakhtjan Apsatarov
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
LOG_FILE="$SCRIPT_DIR/../logs/Bakhtjan_Apsatarov_system_report.log"
mkdir -p "$(dirname "$LOG_FILE")"

{
  echo "=================================================="
  echo " SYSTEM REPORT"
  echo "=================================================="
  echo "Student full name : Bakhtjan Apsatarov"
  echo "Date and time     : $(date '+%Y-%m-%d %H:%M:%S %Z')"
  echo "Current user      : $(whoami)"
  echo "Operating system  : $( (. /etc/os-release 2>/dev/null && echo "$PRETTY_NAME") || uname -s )"
  echo "Kernel version    : $(uname -r)"
  echo
  echo "--- CPU information ---"
  lscpu 2>/dev/null | grep -E 'Model name|^CPU\(s\)|Architecture' || grep -m1 'model name' /proc/cpuinfo
  echo
  echo "--- Memory information ---"
  free -h
  echo
  echo "--- Disk usage ---"
  df -h
  echo
  echo "--- Available disk space on / ---"
  df -h / | awk 'NR==2 {print "Available: " $4 " of " $2 " (used " $5 ")"}'
  echo
} | tee -a "$LOG_FILE"
