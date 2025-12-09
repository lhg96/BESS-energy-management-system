#!/bin/bash

# Right Panel Test Runner
# Standalone test for EMS v2.0 Right Monitoring Panel
# Reference: GUI_Corrections_Applied_Summary.md

echo "=================================="
echo "EMS v2.0 Right Panel Test"
echo "=================================="
echo ""

# Navigate to project directory
cd "$(dirname "$0")"

# Compile the test if needed
if [ ! -f "target/test-classes/com/hepi/hils/gui/RightPanelTest.class" ]; then
    echo "Compiling RightPanelTest..."
    mkdir -p target/test-classes
    javac -d target/test-classes -cp target/classes src/test/java/com/hepi/hils/gui/RightPanelTest.java
    
    if [ $? -ne 0 ]; then
        echo "❌ Compilation failed!"
        exit 1
    fi
    echo "✅ Compilation successful!"
    echo ""
fi

# Run the test
echo "Starting Right Panel Test..."
echo ""
java -cp target/test-classes:target/classes com.hepi.hils.gui.RightPanelTest

echo ""
echo "Test completed."
