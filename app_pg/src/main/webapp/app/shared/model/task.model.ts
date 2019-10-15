import { IJob } from 'app/shared/model/job.model';

export interface ITask {
  id?: number;
  title?: string;
  description?: string;
  jobTasks?: IJob[];
}

export class Task implements ITask {
  constructor(public id?: number, public title?: string, public description?: string, public jobTasks?: IJob[]) {}
}
