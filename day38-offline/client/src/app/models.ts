export interface Task {
    id: number
	task: string
	priority: number
	dueDate: number
}

export interface SyncResult {
	insertCount : number
}