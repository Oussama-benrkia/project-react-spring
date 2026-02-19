import { useEffect, useState } from "react";

const API_URL = import.meta.env.VITE_API_URL;

function App() {
  const [tasks, setTasks] = useState([]);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  // Load tasks
  const loadTasks = async () => {
    try {
      const res = await fetch(`${API_URL}?page=0&size=100`);
      const data = await res.json();
      setTasks(data.data);
    } catch (error) {
      console.error("Error loading tasks", error);
    }
  };

  useEffect(() => {
    loadTasks();
  }, []);

  // Add task
  const addTask = async (e) => {
    e.preventDefault();

    const newTask = {
      title,
      description,
      status: "NEW"
    };

    await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(newTask)
    });

    setTitle("");
    setDescription("");
    loadTasks();
  };

  // Delete task
  const deleteTask = async (id) => {
    await fetch(`${API_URL}/${id}`, {
      method: "DELETE"
    });

    loadTasks();
  };

  return (
    <div style={{ padding: "20px", fontFamily: "Arial" }}>
      <h1>Task Manager</h1>

      <form onSubmit={addTask} style={{ marginBottom: "20px" }}>
        <input
          type="text"
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          style={{ marginLeft: "10px" }}
        />
        <button type="submit" style={{ marginLeft: "10px" }}>
          Add
        </button>
      </form>

      <ul>
        {tasks.map((task) => (
          <li key={task.id} style={{ marginBottom: "10px" }}>
            <strong>{task.title}</strong> - {task.status}
            <button
              onClick={() => deleteTask(task.id)}
              style={{ marginLeft: "10px" }}
            >
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
