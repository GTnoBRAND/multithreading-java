# Multithreading in Java

A comprehensive Java project demonstrating multithreading concepts with practical examples and real-world use cases.

## 📋 Overview

This repository provides in-depth examples and explanations of Java multithreading, covering everything from basic thread creation to advanced synchronization patterns. Learn how to build efficient, concurrent applications using Java's threading capabilities.

## ✨ Features

- **Thread Basics**: Creating and running threads using `Thread` class and `Runnable` interface
- **Thread Synchronization**: Synchronized methods, locks, and thread-safe operations
- **Thread Communication**: Inter-thread communication and coordination
- **Thread Pools & Executors**: Managing multiple threads efficiently with thread pools
- **Real-world Examples**: Practical implementations showing multithreading in action
- **Best Practices**: Industry-standard patterns and common pitfalls to avoid

## 🛠️ Prerequisites

- **Java 25** or higher
- **Maven 3.6+** for dependency management

## 📦 Dependencies

This project uses:
- **JNA** (Java Native Access) v5.19.1 - For native library integration

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/GTnoBRAND/multithreading-java.git
cd multithreading-java
```

### 2. Build the Project

```bash
mvn clean install
```

### 3. Run Examples

```bash
# Compile the project
mvn compile

# Run a specific class
mvn exec:java -Dexec.mainClass="com.example.YourMainClass"
```

## 📚 Project Structure

```
multithreading-java/
├── src/
│   ├── main/
│   │   └── java/              # Main source code
│   └── test/
│       └── java/              # Unit tests
├── pom.xml                    # Maven configuration
└── README.md                  # This file
```

## 🔍 Topics Covered

### Basic Threading
- Thread creation and lifecycle
- Thread states and transitions
- Starting and stopping threads
- Thread naming and priorities

### Synchronization
- Synchronized blocks and methods
- Monitor-based locking
- Race conditions and deadlocks
- Volatile variables

### Advanced Concepts
- `ReentrantLock` and `ReentrantReadWriteLock`
- `Semaphore` and `CountDownLatch`
- `CyclicBarrier` and `Phaser`
- Thread-safe collections

### Executor Framework
- `ExecutorService` and thread pools
- `FixedThreadPool`, `CachedThreadPool`, and `SingleThreadExecutor`
- Future and callable interfaces
- Scheduled execution

## 📖 Example Usage

Here's a simple example of creating and running a thread:

```java
// Method 1: Extend Thread class
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

MyThread thread = new MyThread();
thread.start();

// Method 2: Implement Runnable interface
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread is running");
    }
}

Thread thread = new Thread(new MyRunnable());
thread.start();
```

## 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is open source and available without a specific license. Feel free to use it for learning and educational purposes.

## 🙋 Support

If you have questions or need help:
- Open an [Issue](https://github.com/GTnoBRAND/multithreading-java/issues) on GitHub
- Check the reference materials in `Reference.txt`
- Review the example code for practical patterns

## 📚 Additional Resources

- [Oracle Java Concurrency Tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
- [Java Memory Model](https://docs.oracle.com/javase/specs/jls/se25/html/jls-17.html)
- [Effective Java - Concurrency](https://www.oreilly.com/library/view/effective-java-3rd/9780134685991/)

## 🎯 Learning Path

1. Start with basic thread creation (`Thread` and `Runnable`)
2. Learn about thread synchronization and thread safety
3. Explore the Executor framework for managing threads
4. Study advanced synchronization utilities
5. Review real-world examples and best practices

---

**Last Updated**: August 2026  
**Java Version**: 25  
**Status**: Active Development
