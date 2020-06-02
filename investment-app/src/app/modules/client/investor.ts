import { Fund } from './fund';

export interface Investor {
  id:string;
  name:string;
  email:string;
  funds:Fund[];
}
