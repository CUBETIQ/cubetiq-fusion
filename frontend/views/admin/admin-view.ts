import { html } from 'lit';
import { customElement } from 'lit/decorators.js';
import { View } from '../../views/view';

@customElement('admin-view')
export class AdminView extends View {
  render() {
    return html`<div>Admin</div>`;
  }
}
