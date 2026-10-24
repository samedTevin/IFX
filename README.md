<div align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="assets/ifx-logo-dark.png">
    <source media="(prefers-color-scheme: light)" srcset="assets/ifx-logo-light.png">
    <img src="assets/ifx-logo-light.png" alt="ifx logo" width="130" />
  </picture>

  <h1>ifx</h1>

  <p>
    <strong>If X, then do Y.</strong><br>
    <em>Say it. See it. Test it. Run it.</em><br>
    Turn one sentence into a working Android automation, tested safely and running offline on your phone.
  </p>

  <p>
    <a href="https://github.com/samedTevin/ifx"><img src="https://img.shields.io/badge/Status-In_Development-C6FF3D?style=flat&labelColor=0A0A0A" alt="Status" /></a>
    <a href="https://kotlinlang.org/"><img src="https://img.shields.io/badge/Kotlin-0A0A0A?style=flat&logo=kotlin&logoColor=C6FF3D" alt="Kotlin" /></a>
    <a href="https://developer.android.com/"><img src="https://img.shields.io/badge/Android-0A0A0A?style=flat&logo=android&logoColor=C6FF3D" alt="Android" /></a>
    <a href="https://ai.google.dev/"><img src="https://img.shields.io/badge/Gemini_API-0A0A0A?style=flat&logo=googlegemini&logoColor=C6FF3D" alt="Gemini" /></a>
    <a href="https://firebase.google.com/"><img src="https://img.shields.io/badge/Firebase_Auth-0A0A0A?style=flat&logo=firebase&logoColor=C6FF3D" alt="Firebase" /></a>
  </p>

  <p>
    <a href="#-overview">Overview</a> ·
    <a href="#-key-features">Features</a> ·
    <a href="#%EF%B8%8F-technology-stack">Tech Stack</a> ·
    <a href="#%EF%B8%8F-roadmap">Roadmap</a>
  </p>
</div>

> [!WARNING]
> **ifx** is still under active development and is not ready for use. Features and structure may change without notice.

---

## 📌 Overview

**ifx** is a native Android app, built in Kotlin, for people who want automation without the learning curve. Describe what you want in plain language, review it as a visual workflow, test it safely, and let it run on your phone, even offline.

The name is the idea: **if x, then do y**. Every automation follows one simple model:

```
Trigger (When) → Condition (If) → Action (Do)
```

> **Example:** When a deadline is close, and it's still unfinished, send me a reminder.

Tools like Tasker, MacroDroid, IFTTT, and Zapier are powerful, but users often report steep learning curves, triggers that fail, and no safe way to test a rule before it goes live. **ifx** is built to fix exactly that.

---

## ✨ Key Features

- **Natural Language Creator:** Speak or type your intent and the Gemini API drafts the automation for you.
- **Visual Workflow Builder:** A clean card-and-node canvas where you can see, edit, and branch your logic at a glance.
- **Dry-Run Sandbox:** One-click simulation with mock data catches broken dates and empty fields before activation.
- **Offline Notebook & Engine:** Local task storage plus calendar and notification execution that works without internet.

---

## 🛠️ Technology Stack

| Layer | Technology |
| :--- | :--- |
| **Language** | Kotlin (Android) |
| **AI** | Gemini API |
| **Authentication** | Firebase Authentication |
| **Storage & Execution** | On-device database and local engine |
| **Optional** | Kotlin Multiplatform shared core |

---

## 🗺️ Roadmap

### Term 1: Functional Android
- [ ] **Foundation:** project setup, Firebase Auth, onboarding
- [ ] **AI & Notebook:** Gemini pipeline, offline notebook, engine
- [ ] **Builder & Dry-Run:** visual canvas and sandbox
- [ ] **Real Actions:** calendar and notification dispatch

### Term 2: Advanced Android Capabilities
- [ ] Sensor triggers such as heart rate via Health Connect
- [ ] Multi-app notification triggers (WhatsApp, Telegram, Instagram, and more)
- [ ] Extensible architecture for adding new apps and triggers

---

## 🚀 Setup & Installation

Build instructions will be added once the first working version is ready.

```bash
git clone https://github.com/samedTevin/ifx.git
```

---

## 🎓 About

Graduation project (**SWE497**) by **Samed Tevin**, Department of Software Engineering, Altınbaş University.  
Supervised by **Asst. Prof. Dr. Eman Alyasin**.

---

## 📄 License

Not chosen yet. All rights reserved by the author until a license is added.

<br>

<div align="center">
  <sub>Built with care · <b>if x, then do y</b></sub>
</div>