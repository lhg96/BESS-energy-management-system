#!/bin/bash

# MainForm Run Script
# EMS v2.0 Full Application
# Includes improved right monitoring panel

echo "=================================="
echo "EMS v2.0 - Full Application"
echo "=================================="
echo ""

# Navigate to project directory
cd "$(dirname "$0")"

# Check if compiled
if [ ! -d "target/classes" ]; then
    echo "⚠️  Project not compiled. Compiling now..."
    mvn clean compile
    
    if [ $? -ne 0 ]; then
        echo "❌ Compilation failed!"
        exit 1
    fi
    echo "✅ Compilation successful!"
    echo ""
fi

# Run MainForm
echo "Starting EMS v2.0 MainForm..."
echo ""
echo "Features:"
echo "  ✅ 4-Panel Layout (Header, Left Control, Center Tabs, Right Monitor)"
echo "  ✅ Improved Right Panel with 20pt fonts"
echo "  ✅ Real-time data simulation"
echo "  ✅ English-only interface"
echo "  ✅ Dynamic status updates"
echo ""

mvn exec:java -Dexec.mainClass="org.hils.gui.MainForm" -q

echo ""
echo "Application closed."
