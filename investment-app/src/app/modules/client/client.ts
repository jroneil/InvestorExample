import { Investor } from './investor';

export interface Client {
   id: string;
   clientName: string;
   description: string;
   createdOn:string;
   updatedOn:string;
   investors:Investor[];
}
