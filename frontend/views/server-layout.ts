import { html } from 'lit';
import { customElement } from 'lit/decorators.js';
import {Layout} from "./view";

@customElement('server-layout')
export class AdminView extends Layout {
    render() {
        return html`<><slot></slot></>`;
    }
}