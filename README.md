# Message Terminal

A console-based chat application written in Java. Users can create an account, log in, post messages to a shared chat log, and read all messages posted by everyone. Accounts and messages are persisted to plain-text files, so data survives between runs.

This was built as a practice project to work with file I/O, user input handling, and basic account management in Java.

## Features

- **Account creation** — register a new username and password, with password confirmation to catch typos.
- **Login** — authenticate against stored credentials before accessing the chat.
- **Send messages** — post a message to the shared chat; each entry is tagged with the author's username and a timestamp.
- **View messages** — read the full chat history posted by all users.
- **Persistent storage** — accounts and messages are saved to text files and reloaded automatically on the next run.

## How it works

The program runs as a loop in the terminal. On launch, the user is prompted to either log in or create an account. Once authenticated, a menu lets them send a message, view all messages, or exit.

- **Accounts** are stored in `Accounts.txt`, one per line, in the format `username|password`.
- **Messages** are stored in `texts.txt` in the format `username|message[timestamp]`.

Authentication works by reading all stored accounts into a list and checking whether the entered `username|password` combination exists. Messages are appended to the message file as they are sent and read back in order when the chat history is viewed.

## Project structure

```
.
├── MessageTerminal.java   # Main program: menu loop, login, account creation, send/read
├── Message.java           # Message object (username, message text, timestamp)
├── Accounts.txt           # Stored user credentials (generated at runtime)
└── texts.txt              # Stored chat messages (generated at runtime)
```

> Note: `Accounts.txt` and `texts.txt` are created automatically the first time an account is registered or a message is sent.

## Getting started

### Prerequisites

- Java Development Kit (JDK) 8 or later installed and on your `PATH`.

### Compile

```bash
javac MessageTerminal.java Message.java
```

### Run

```bash
java MessageTerminal
```

### Example session

```
Welcome to the Chat terminal please press 1 to enter your user/password or 2 to create an account
2
Please enter a username
alice
Please create a password
hunter2
please confirm password
hunter2
Thank you, your username and password have been recorded!

Press 1 to send a text, 2 to view all texts and 3 to exit
1
What message would you like to send to the chat?
Hello everyone!

Press 1 to send a text, 2 to view all texts and 3 to exit
2
alice|Hello everyone![14:32]
```

## Menu options

| Option | Action |
|--------|--------|
| `1` | Send a message to the chat |
| `2` | View all messages |
| `3` | Exit the program |

## Known limitations & possible improvements

This is a learning project, and there are a few areas that would be worth strengthening in a future version:

- **Passwords are stored in plain text.** A production version would hash and salt passwords rather than storing them directly.
- **No input validation on the `|` delimiter.** Because accounts and messages use `|` as a separator, a username or message containing `|` could corrupt the stored format.
- **Mixed `Scanner` usage.** The program creates several `Scanner` instances on `System.in`; consolidating to a single shared `Scanner` would avoid subtle input-buffering issues.
- **No way to delete or edit messages**, and the chat is global rather than per-conversation.

## What I learned

- Reading from and writing to files with `FileWriter`, `FileReader`, and `BufferedReader`
- Handling user input with `Scanner` and validating menu choices
- Structuring a program across multiple methods and a supporting class
- Persisting and reloading application state between runs
- Working with `LocalTime` for timestamps

