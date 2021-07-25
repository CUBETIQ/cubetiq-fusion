import { html } from 'lit';
import { customElement } from 'lit/decorators.js';
import { View } from '../../views/view';

@customElement('profile-view')
export class ProfileView extends View {
  render() {
    return html`<div>Profile</div>`;
  }
}
