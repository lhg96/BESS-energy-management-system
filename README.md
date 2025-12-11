# HILS - Hardware-in-the-Loop Simulation for ESS

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Maven](https://img.shields.io/badge/Maven-Build-blue.svg)
![License](https://img.shields.io/badge/License-Proprietary-red.svg)
![Version](https://img.shields.io/badge/Version-2.0-green.svg)

**300MW Battery Energy Storage System (BESS) Energy Management System**

Real-time monitoring and control system for battery energy storage systems

</div>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Screenshots](#-screenshots)
- [Features](#-features)
- [Architecture](#-architecture)
- [System Requirements](#-system-requirements)
- [Installation](#-installation)
- [Usage](#-usage)
- [Project Structure](#-project-structure)
- [User Interface](#-user-interface)
- [Documentation](#-documentation)
- [Development](#-development)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🎯 Overview

HILS (Hardware-in-the-Loop Simulation) EMS is a comprehensive **Energy Management System** designed for monitoring and controlling a 300MW Battery Energy Storage System (BESS). The system provides real-time control, monitoring, and analysis capabilities for large-scale energy storage installations.

### Key Highlights

- **Real-time Control**: Direct control of battery charging/discharging and PCS (Power Conversion System) operations
- **Comprehensive Monitoring**: Live tracking of power, frequency, voltage, temperature, and battery SOC (State of Charge)
- **Advanced UI**: Modern 4-panel layout with intuitive controls and visual feedback
- **Scalability**: Supports multiple battery packs and PCS units
- **Safety Features**: Multi-level emergency stop system and comprehensive alarm management

---

## 📸 Screenshots

### Software Interface

#### Main Application Interface
![Main Panel](screenshots/ems_main_monitoring_panel.png)
*HILS EMS v2.0 - Complete 4-panel layout showing battery control, center tabs, and real-time monitoring*

#### Battery Pack SOC Control Panel
![Battery Pack SOC Panel](screenshots/battery_pack_soc_panel.png)
*Left control panel - Battery SOC monitoring and charge/discharge control*

#### EMS Control Interface
![EMS Control](screenshots/ems_control_panel.png)
*Complete control interface with battery and PCS management*

#### Right Monitoring Panel
![Right Panel](screenshots/ems_right_monitoring_panel.png)
*Real-time monitoring - Power, frequency, voltage, temperature, alarms, and events*

#### HILS Battery Simulator
![Battery Simulator](screenshots/hils_battery_simulator_screen.jpeg)
*Real-time battery simulation interface with cycle testing*

#### Battery Cycle BTS Client
![BTS Client](screenshots/battery_cycle_bts_client_screen.jpeg)
*Battery Testing System client interface for cycle management*

### Hardware & Laboratory Setup

#### HILS Cabinet and Rack Configuration
![HILS Cabinet](screenshots/hils_cabinet_and_rack_setup.jpeg)
*Complete HILS hardware cabinet with battery simulator and control systems*

#### HILS Laboratory Overview
![Lab Overview](screenshots/hils_lab_racks_with_chamber.jpeg)
*Laboratory setup showing equipment racks and thermal chamber*

#### Power Monitoring Station
![Power Station](screenshots/hils_lab_power_monitoring_station.jpeg)
*Dedicated power monitoring station for real-time data acquisition*

#### Server Rack Console
![Server Rack](screenshots/hils_server_rack_console_open.jpeg)
*Server rack with open console showing system configuration*

#### Thermal Chamber Interior
![Thermal Chamber](screenshots/thermal_chamber_interior_empty.jpeg)
*Environmental thermal chamber for battery testing under controlled conditions*

#### Thermal Monitor Device
![Thermal Monitor](screenshots/thermal_monitor_device_in_cabinet.jpeg)
*Temperature monitoring device installed in cabinet*

### Battery & Power Systems

#### Battery Module Stack
![Battery Stack](screenshots/battery_module_stack_on_floor.jpeg)
*Physical battery module stack for testing and validation*

#### DC Power Supply Wiring
![DC Wiring](screenshots/rack_dc_supplies_wiring.jpeg)
*Rack-mounted DC power supplies with organized wiring*

### Environmental Monitoring System

#### LoRa Environment Sensor (Outdoor)
![Outdoor Sensor](screenshots/rooftop_env_sensor_tripod_solar.jpeg)
*Rooftop environmental sensor with tripod mount and solar panel*

#### Indoor Environment Sensor
![Indoor Sensor](screenshots/indoor_env_sensor_with_solar_pole.jpeg)
*Indoor environmental monitoring sensor with solar power*

#### Environment Sensor PCB (RAK4630)
![Sensor PCB](screenshots/env_sensor_pcb_rak4630_closeup.jpeg)
*Close-up of RAK4630-based environmental sensor PCB*

#### Sensor Head Assembly
![Sensor Head](screenshots/env_sensor_head_top_closeup.jpeg)
*Top view of environmental sensor head with components*

#### Solar Node Components
![Solar Node](screenshots/solar_node_components_window_light.jpeg)
*Solar-powered sensor node components in natural light*

#### LoRa Sensor on Workbench
![Workbench](screenshots/lora_env_sensor_workbench.jpeg)
*LoRa environmental sensor development on workbench*

#### Desk Solar Panel Prototype
![Solar Proto](screenshots/desk_solar_panel_proto.jpeg)
*Desktop solar panel prototype for sensor power testing*

### Data Acquisition & Analysis

#### DAQ Vibration Logger
![Vibration Logger](screenshots/daq_vibration_logger_laptop.jpeg)
*Data acquisition system for vibration logging and analysis*

#### Emissions Analyzer Station
![Emissions Station](screenshots/emissions_analyzer_station_overview.jpeg)
*Complete emissions analyzer station for environmental monitoring*

#### Emissions Analyzer (Close-up)
![Emissions Cart](screenshots/emissions_analyzer_cart_close.jpeg)
*Mobile emissions analyzer cart with measurement equipment*

---

## ✨ Features

### 🔋 Battery Management
- Real-time SOC monitoring and control
- Battery pack health status tracking
- Charge/discharge power control (-300MW to +300MW)
- Target SOC setting with slider controls
- Individual pack balancing and diagnostics

### ⚡ PCS Control
- Power Conversion System real-time control
- Multiple operation modes (Standby, Constant Power, Constant Frequency, Voltage Control, Auto)
- Frequency regulation (59.5-60.5Hz)
- Power output control with visual feedback
- System diagnostics and advanced configuration

### 📊 Monitoring & Visualization
- Real-time power trend charts
- SOC history visualization
- Frequency stability monitoring
- Grid interconnection status
- System efficiency tracking

### 🚨 Alarm & Event Management
- Real-time alarm monitoring with priority classification
- Event logging with timestamps
- Visual status indicators (color-coded)
- System health status dashboard

### ⚙️ System Configuration
- User permission management (Admin, Operator, Monitor)
- Communication protocol settings (Modbus TCP, DNP3, IEC 61850)
- System parameter configuration
- Email/SMS notification setup

### 🛡️ Safety Features
- **Multi-level Emergency Stop System**:
  - Individual PCS emergency stop
  - Overall system emergency stop
  - Individual battery pack isolation
- **Real-time Diagnostics**:
  - PCS diagnostic reports
  - Battery pack health checks
  - Overall system status summary
- **Comprehensive Alarm System**: Priority classification, email notifications

### 📈 Performance & Quality
- **Efficient UI Updates**: Redraw only changed components
- **Memory Management**: Timer and resource cleanup
- **Scroll Optimization**: Support for large data volumes
- **Immediate Feedback**: Confirmation messages for all control actions
- **Error Handling**: User-friendly error messages and recovery guides

---

## 🏗️ Architecture

### System Components

```
┌─────────────────────────────────────────────────────┐
│                   HILS EMS v2.0                     │
├──────────────┬──────────────────┬───────────────────┤
│  Left Panel  │   Center Panel   │   Right Panel     │
│              │                  │                   │
│  Battery     │   System Tabs    │   Monitoring      │
│  Control     │   - Overview     │   - Power         │
│              │   - Battery      │   - Frequency     │
│  PCS         │   - PCS Detail   │   - Voltage       │
│  Control     │   - Grid         │   - Temperature   │
│              │   - Charts       │   - Alarms        │
│              │   - Config       │   - Events        │
└──────────────┴──────────────────┴───────────────────┘
```

### Core Class Structure

```
MainForm.java (Main UI Container)
├── Top Panel (Header) - java MainForm top
│   └── Time and system status display
├── West Panel (Left Control) - java MainForm west
│   ├── BatteryPackSOCPanel.java (Battery control)
│   └── PCSControlPanel.java (PCS control)
├── Center Panel (Tabs) - java MainForm center
│   ├── System Overview (PCSBMSSummery.java)
│   ├── BatteryStatusPanel.java
│   ├── PCSDetailStausPanel.java
│   ├── GridMonitorPanel.java
│   ├── RealTimeChartPanel.java
│   └── SystemConfigPanel.java
├── East Panel (Right Monitoring) - java MainForm east
│   ├── Real-time Data Panels (4 panels)
│   └── Alarm & Event Panels (2 panels)
└── Bottom Panel (Status Bar)
    └── System status and connection info
```

### Technology Stack

- **Language**: Java 21
- **Build Tool**: Maven 3.9.x
- **UI Framework**: Java Swing
- **Charting**: JFreeChart 1.0.13
- **Database**: InfluxDB (embedded) 1.3.1
- **Logging**: Log4j 1.2.17 with SLF4J

### Data Flow

1. **Simulation-Based**: Currently uses internal data model without actual equipment connection
2. **Timer-Based Updates**: Periodic updates using `javax.swing.Timer`
   - Header clock: 1 second
   - Battery data: 5 seconds
   - PCS data: 2 seconds
   - Right panel monitoring: 2 seconds
3. **Event-Driven**: Immediate response to user operations with confirmation dialogs

### Independent Panel Execution

Each panel can be run independently for testing and development:

```bash
# Run specific panel only
java -cp target/classes org.hils.gui.MainForm top      # Header panel
java -cp target/classes org.hils.gui.MainForm west     # Left control
java -cp target/classes org.hils.gui.MainForm center   # Center tabs
java -cp target/classes org.hils.gui.MainForm east     # Right monitoring
```

---

## 💻 System Requirements
### Technology Stack

- **Language**: Java 21
- **Build Tool**: Maven
- **GUI Framework**: Java Swing
- **Charts**: JFreeChart 1.0.13
- **Database**: InfluxDB (embedded)
- **Utilities**: Lombok 1.18.26

---

## 💻 System Requirements

### Runtime Environment
- **Java**: JDK 21 or higher
- **OS**: Windows, macOS, or Linux
- **Memory**: 4GB RAM minimum (8GB recommended)
- **Display**: 1920x1080 or higher resolution

### Development Environment
- **Maven**: 3.6+ for building
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code with Java extensions

---

## 🚀 Installation

### 1. Clone the Repository
```bash
git clone <repository-url>
cd HILS
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn exec:java -Dexec.mainClass="org.hils.App"
```

Or run directly from JAR:
```bash
java -jar target/HILS-0.0.1-SNAPSHOT.jar
```

---

## 📖 Usage

### Starting the Application

#### Full Application (Recommended)
```bash
# Using convenience script
./run-mainform.sh

# Or using Maven directly
mvn exec:java -Dexec.mainClass="org.hils.gui.MainForm"

# Or using Java directly
java -cp target/classes org.hils.gui.MainForm
```

#### Test Right Monitoring Panel
```bash
# Standalone right panel test with real-time simulation
./run-right-panel-test.sh

# Or using Java directly
java -cp target/test-classes:target/classes org.hils.gui.RightPanelTest
```

#### Individual Panel Testing
The application supports independent panel execution for development and testing:

```bash
# Test header panel only
java -cp target/classes org.hils.gui.MainForm top

# Test left control panel
java -cp target/classes org.hils.gui.MainForm west

# Test center tab panel
java -cp target/classes org.hils.gui.MainForm center

# Test right monitoring panel  
java -cp target/classes org.hils.gui.MainForm east
```

# Test center panel only
java org.hils.gui.MainForm center

# Test right monitoring panel only
java org.hils.gui.MainForm east

# Test left control panel only
java org.hils.gui.MainForm west


### Basic Operations

1. **Battery Control**
   - Adjust target SOC using the slider (20-100%)
   - Set charge/discharge power (-300 to +300 MW)
   - Click "Start Charge" or "Start Discharge"
   - Monitor real-time battery status

2. **PCS Control**
   - Select operation mode from dropdown
   - Adjust power output using slider
   - Set target frequency (59.5-60.5 Hz)
   - Click "Start Operation" to begin

3. **Monitoring**
   - View real-time data in right panel
   - Check alarm status
   - Review event logs
   - Monitor system efficiency

4. **Emergency Stop**
   - Click "Emergency Stop" button (red)
   - Available in both battery and PCS control panels
   - Immediately halts all operations

---

## 📁 Project Structure

```
HILS/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/
│   │           └── hils/
│   │               ├── App.java                    # Application entry point
│   │               ├── gui/
│   │               │   ├── MainForm.java          # Main UI container
│   │               │   ├── chart/
│   │               │   │   └── RealTimeChartPanel.java
│   │               │   ├── config/
│   │               │   │   └── SystemConfigPanel.java
│   │               │   ├── controls/
│   │               │   │   ├── BatteryPackSOCPanel.java
│   │               │   │   ├── PCSControlPanel.java
│   │               │   │   └── ...
│   │               │   ├── panels/
│   │               │   │   ├── BatteryStatusPanel.java
│   │               │   │   ├── GridMonitorPanel.java
│   │               │   │   ├── PCSDetailStausPanel.java
│   │               │   │   └── ...
│   │               │   └── ...
│   │               ├── property/
│   │               │   └── HProperty.java          # System properties
│   │               └── vo/
│   │                   ├── BMS.java
│   │                   ├── PCS.java
│   │                   └── ...
│   └── test/
│       └── java/
│           └── org/hils/gui/      # UI test classes
├── pom.xml                              # Maven configuration
├── README.md                            # This file
├── EMS_Screen_Configuration_Analysis_Consolidated.md
└── EMS_UI_Checklist.md
```

---

## 🖥️ User Interface

### 4-Panel Layout

The HILS EMS v2.0 features a modern 4-panel layout:

#### 1. Header Panel (Top)
- System title and identification
- Real-time clock display
- System status indicator

#### 2. Left Control Panel (350px)
- **Battery Control (Top 55%)**
  - SOC display and control
  - Charge/discharge power settings
  - Control buttons (Charge, Discharge, Stop, E-Stop)
  
- **PCS Control (Bottom 45%)**
  - Power output control
  - Frequency regulation
  - Operation mode selection
  - Control buttons (Start, Stop, Reset, E-Stop, Diagnostics)

#### 3. Center Tab Panel (Main Content)
- **System Overview**: PCS/BMS summary
- **Battery Status**: Detailed battery monitoring
- **PCS Detail**: PCS system control
- **Grid Monitor**: Grid interconnection status
- **Real-time Charts**: Power, SOC, frequency trends
- **System Config**: Settings and configuration

#### 4. Right Monitoring Panel (350px)
- Real-time data panels (Power, Frequency, Voltage, Temperature)
- Alarm status panel
- Event log panel

#### 5. Status Bar (Bottom)
- Connection status
- Data status
- System version
- Overall system state

### Color Coding
- 🟢 **Green**: Normal operation (SOC > 80%)
- 🟠 **Orange**: Caution (SOC 50-80%)
- 🔴 **Red**: Warning (SOC < 20%) or fault condition
- ⚫ **Gray**: Inactive or stopped

---

## 📚 Documentation

Comprehensive documentation is available in the `docs` folder:

- **[EMS Screen Configuration Analysis](docs/EMS_Screen_Configuration_Analysis_Consolidated.md)**
  - Detailed UI component analysis
  - Screen structure and layout
  - Data structures and class mappings
  - Technical implementation details

- **[UI Checklist](docs/EMS_UI_Checklist.md)**
  - Completed features checklist
  - Pending improvements
  - Development roadmap

- **[GUI Issues and Corrections](docs/GUI_Issues_And_Corrections.md)**
  - Identified GUI implementation issues
  - Correction requirements
  - Implementation checklist

- **[GUI Corrections Applied Summary](docs/GUI_Corrections_Applied_Summary.md)**
  - Summary of applied corrections
  - Label conversions (Korean to English)
  - Panel identification comments

- **[Right Panel Improvements](docs/Right_Panel_Improvements_Applied.md)**
  - Detailed right panel enhancements
  - Font size improvements (16pt → 20pt)
  - Real-time simulation features

---

## 🛠️ Development

### Building from Source

```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package JAR
mvn package

# Install to local repository
mvn install
```

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=ImageLoadTest
```

---

## 🖥️ Screen Configuration (v2.0)

### Main Frame Layout

The EMS v2.0 implements a modern 4-panel layout optimized for real-time control and monitoring:

```
┌─────────────────────────────────────────────────────────────────────────┐
│                         Header Panel                                    │
│  🔋 EMS | 300MW BESS | 🟢 System Normal | 2024.10.29 15:30               │
│          Real-time Monitoring & Control System                          │
├──────────────┬───────────────────────────────────┬──────────────────────┤
│ Left Control │      Center Tab Panel             │ Right Monitoring     │
│  (350px)     │                                   │     (350px)          │
│              │                                   │                      │
│⚡ Battery     │ [System][Battery][PCS][Grid]      │ Power: 150.5MW       │
│  & PCS       │ [Charts][Config]                  │ Freq: 60.05Hz        │
│  Control     │                                   │ Voltage: 22.9kV      │
│              │   Current Tab Content             │ Temp: 25.3°C         │
│ Battery:     │                                   │                      │
│ - SOC Target │                                   │ Alarm Status         │
│ - Power Set  │                                   │ Event Log            │
│ - Charge     │                                   │                      │
│ - Discharge  │                                   │                      │
│ - Stop       │                                   │                      │
│ - E-STOP     │                                   │                      │
│              │                                   │                      │
│ PCS:         │                                   │                      │
│ - Set Power  │                                   │                      │
│ - Frequency  │                                   │                      │
│ - Mode       │                                   │                      │
│ - Start/Stop │                                   │                      │
│ - Diag       │                                   │                      │
├──────────────┴───────────────────────────────────┴──────────────────────┤
│                         Status Bar                                      │
│ 🔌 Connected | 📊 Data Normal | ⚠️ No Alarms    EMS v2.0 | Ready         │
└─────────────────────────────────────────────────────────────────────────┘
```

### Panel Details

#### Header Panel
- **Purpose**: System identification, status, and time information
- **Components**:
  - Left: 🔋 EMS icon and system name
  - Center: "300MW BESS Energy Management System" title
  - Right: 🟢 System status + real-time clock (yyyy.MM.dd HH:mm:ss)
- **Update Cycle**: Clock updates every 1 second, status every 5 seconds

#### Left Control Panel (350px width)
**Purpose**: Real-time direct battery and PCS control

**Battery Control Panel** (Top 55%):
- Total SOC display (85.2%)
- Average voltage display (3.85V)
- Temperature monitoring (25.3°C)
- Target SOC slider (20-100%)
- Charge/discharge power setting (±300MW)
- Control buttons: Charge, Discharge, Stop, Emergency Stop

**PCS Control Panel** (Bottom 45%):
- Output power display (150.5MW)
- Frequency display (60.0Hz)
- Voltage/efficiency monitoring
- Set power slider (±300MW)
- Target frequency spinner (59.5-60.5Hz)
- Operation mode selection (Standby, Power Control, Frequency Control, etc.)
- Control buttons: Start, Stop, Reset, E-STOP, Diagnostics, Config

#### Center Tab Panel
**6 tabs for detailed system management**:

1. **System Overview**: PCS/BMS summary + additional controls
2. **Battery Status**: Detailed battery system monitoring
3. **PCS Detail Control**: PCS detailed control and monitoring
4. **Grid Monitor**: Power grid interconnection monitoring
5. **Real-time Charts**: Power, SOC, frequency visualization
6. **System Config**: System parameters and settings management

#### Right Monitoring Panel (350px width)
**Real-time data monitoring** (No controls):

**Top Section** (4 panels):
- ⚡ Power Monitoring: 150.5MW, Charging status
- 📊 Frequency Monitoring: 60.05Hz, Normal status
- 🔌 Voltage Monitoring: 22.9kV, Normal status
- 🌡️ Temperature Monitoring: 25.3°C, Normal status

**Bottom Section** (2 panels):
- ⚠️ Real-time Alarms: Active alarm status
- 📋 System Events: Event log with timestamps

#### Status Bar
- **Left**: 🔌 Connection, 📊 Data, ⚠️ Alarm status
- **Right**: EMS v2.0 version, System ready status

### Data Structures

#### Battery-Related
- **BatteryStatus**: SOC, SOH, voltage, current, temperature, cell min/max voltage
- **BatteryPackInfo**: Pack ID, capacity, health status

#### PCS-Related
- **PCSControlData**: PCS ID, online status, operation mode, power, voltage, current, frequency
- **PCSSummery**: PCS summary data
- **PCSStatus**: Detailed PCS status

#### System Configuration
- **HProperty**: Screen size, supply power (300MW)
- **SystemConfigPanel**: Protocols (Modbus TCP, DNP3, IEC 61850), user management, alarms

### Class Mapping

| Screen Component | Class File | Location |
|------------------|------------|----------|
| Main Frame | MainForm.java | `org.hils.gui` |
| Battery Control | BatteryPackSOCPanel.java | `org.hils.gui.controls` |
| PCS Control | PCSControlPanel.java | `org.hils.gui.controls` |
| Battery Status Tab | BatteryStatusPanel.java | `org.hils.gui.panels` |
| PCS Detail Tab | PCSDetailStausPanel.java | `org.hils.gui.panels` |
| Grid Monitor Tab | GridMonitorPanel.java | `org.hils.gui.panels` |
| Real-time Charts | RealTimeChartPanel.java | `org.hils.gui.chart` |
| System Config | SystemConfigPanel.java | `org.hils.gui.config` |
| Right Monitoring | MainForm.java | Integrated in main form |


---

## 📄 License

**Custom License - Free for Personal Use, Commercial License Required**

This software is free to use for personal, educational, and non-commercial purposes. Commercial use requires a separate license agreement.

- ✅ **Free**: Personal use, education, research
- ❌ **Requires License**: Commercial use, production deployment, integration into commercial products

For commercial licensing inquiries, please contact: hyun.lim@okkorea.net

---

## 📞 Contact & Services

### Development Consulting & Outsourcing Available

We provide professional consulting and development services for IoT, AI, and embedded systems projects.

### 👨‍💼 Project Manager Contact

- **Email**: [hyun.lim@okkorea.net](mailto:hyun.lim@okkorea.net)
- **Homepage**: [https://www.okkorea.net](https://www.okkorea.net)
- **LinkedIn**: [https://www.linkedin.com/in/aionlabs/](https://www.linkedin.com/in/aionlabs/)

### 🛠️ Technical Expertise / 기술 전문 분야

- **IoT System Design and Development** / IoT 시스템 설계 및 개발
- **Embedded Software Development** / 임베디드 소프트웨어 개발 (Arduino, ESP32)
- **AI Service Development** / AI 서비스 개발 (LLM, MCP Agent)
- **Cloud Service Architecture** / 클라우드 서비스 구축 (Google Cloud Platform)
- **Hardware Prototyping** / 하드웨어 프로토타이핑

### 💼 Services / 서비스

- **Technical Consulting** / 기술 컨설팅
  - IoT project planning and design consultation / IoT 프로젝트 기획 및 설계 자문
  - System architecture design / 시스템 아키텍처 설계
  
- **Development Outsourcing** / 개발 외주
  - Full-stack development from firmware to cloud / 펌웨어부터 클라우드까지 Full-stack 개발
  - Proof of Concept (PoC) development / 개념 검증 개발
  - Production-ready system development / 상용 시스템 개발

---

<div align="center">

**Built with ❤️ for Energy Storage Management**

[Documentation](./docs/EMS_Screen_Configuration_Analysis_Consolidated.md) • [Checklist](./docs/EMS_UI_Checklist.md)

</div>